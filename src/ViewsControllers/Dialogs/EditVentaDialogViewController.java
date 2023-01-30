package ViewsControllers.Dialogs;

import Resource.Conection;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.persistence.Query;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EditVentaDialogViewController {
    
    private JLabel Codigo;
    private JLabel Descripcion;
    private JLabel Unidades;
    private JTextField Existencia;
    private JTextField DescuentoPorcentaje;
    private JTextField DescuentoLempiras;
    private JTextField Precio;
    private JTextField Cantidad;
    private JTextField Subtotal;
    private JLabel Error;

    public EditVentaDialogViewController(JLabel Codigo, JLabel Descripcion, JLabel Unidades, JTextField Existencia, JTextField DescuentoPorcentaje, JTextField DescuentoLempiras, JTextField Precio, JTextField Cantidad, JTextField Subtotal, JLabel Error) {
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.Unidades = Unidades;
        this.Existencia = Existencia;
        this.DescuentoPorcentaje = DescuentoPorcentaje;
        this.DescuentoLempiras = DescuentoLempiras;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Subtotal = Subtotal;
        this.Error = Error;
    }
    
    public void setValues(Object[] values){
        Query query = Conection.CreateEntityManager().createEntityManager()
                    .createNativeQuery("SELECT cantidad FROM inventario WHERE ProductoID = "+Integer.valueOf(values[0].toString()));
        Object existencia = query.getSingleResult();
        
        Codigo.setText(values[0].toString());
        Descripcion.setText(values[1].toString());
        Unidades.setText(values[2].toString());
        Existencia.setText(getNumberFormat(Float.parseFloat(existencia.toString())));
        
        Cantidad.setText(values[3].toString());
        Precio.setText(values[4].toString());
        DescuentoLempiras.setText(values[5].toString());
        updateLempirasPorcentajes();
        
        float cantidad = Float.parseFloat(values[3].toString().replace(",", ""));
        float precio = Float.parseFloat(values[4].toString().replace(",", ""));
        float descuento = Float.parseFloat(values[5].toString().replace(",", ""));
        
        Subtotal.setText(getNumberFormat((cantidad * precio) - descuento));
    }
    
    public Object[] getValues(){
        Object[] values = {
            Cantidad.getText().replace(",", ""),
            DescuentoLempiras.getText().replace(",", ""),
            Subtotal.getText().replace(",", "").replace("Lps.", "")
        };
        return values;
    }
    
    public Object[] getValuesforCompra(){
        Object[] values = {
            Cantidad.getText().replace(",", ""),
            Precio.getText().replace(",", ""),
            DescuentoLempiras.getText().replace(",", ""),
            Subtotal.getText().replace(",", "").replace("Lps.", "")
        };
        return values;
    }
    
    public void updateSubtotal(){
        if(validate()){
            Error.setBackground(Color.white);
            float descuento = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float subtotal = (precio * cantidad) - descuento;
            Subtotal.setText(getNumberFormat(subtotal)+" Lps.");
        }
    }
    
    public void updatePorcentajeLempiras(){
        if(validate()){
            float descuento = Float.parseFloat(DescuentoPorcentaje.getText().replace(",", ""));
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float DescuentoEnLempiras = (precio * cantidad) * (descuento * 0.01f);
            DescuentoLempiras.setText(getNumberFormat(DescuentoEnLempiras));
        }
    }
    
    public void updateLempirasPorcentajes(){
        if(validate()){
            float descuento = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float DescuentoEnPorcentaje = (descuento) / ((precio * cantidad) / 100);
            DescuentoPorcentaje.setText(getNumberFormat(DescuentoEnPorcentaje));
        }
    }
    
    private boolean validate(){
        try{
            float descuentoPorcentaje = Float.parseFloat(DescuentoPorcentaje.getText().replace(",", ""));
            if(descuentoPorcentaje < 0 && descuentoPorcentaje > 101){
                Error.setText("El descuento en porcentaje debe de ser mayor a cero y menor a 100");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
        }catch(NumberFormatException ex){
            Error.setText("El descuento en porcentaje debe de ser un numero");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }

        try{
            float descuentoLempiras = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
            if(descuentoLempiras < 0){
                Error.setText("El descuento debe de ser mayor a cero");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
        }catch(NumberFormatException ex){
            Error.setText("El descuento en lempiras debe de ser un numero");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }

        try{
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            if(precio <= 0){
                Error.setText("El precio debe de ser mayor a cero");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
        }catch(NumberFormatException ex){
            Error.setText("El precio debe de ser un numero");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }

        try{
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            if(cantidad <= 0){
                Error.setText("La Cantidad debe de ser mayor a cero");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
            float existencia = Float.parseFloat(Existencia.getText().replace(",", " "));
            if(cantidad > existencia){
                Error.setText("La cantidad debe de ser menor a la existencia actual");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
        }catch(NumberFormatException ex){
            Error.setText("La cantidad del producto debe de ser un numero");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }
        return true;
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}