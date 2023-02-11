package ViewsControllers.Panels.Estadisticas;

import Resource.Conection;
import Resource.Utilities;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class ResumenesViewController {
    private JLabel Cargando;
    private JComboBox Tipo;
    private JComboBox Tiempo;
    private JPanel Grafico;

    public ResumenesViewController(JLabel Cargando, JComboBox Tipo, JComboBox Tiempo, JPanel Grafico) {
        this.Cargando = Cargando;
        this.Tipo = Tipo;
        this.Tiempo = Tiempo;
        this.Grafico = Grafico;
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    public void loadDayGrafic(){
        setLoad(true);
        Runnable run = () -> {
            List<Object[]> registros = getResultListOfProcedure();
            DefaultCategoryDataset datos = new DefaultCategoryDataset();
            String[] columnKeys = {"6 am", "7 am", "8 am", "9 am", "10 am", "11 am", "12 m",
                                   "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm"};
            registros.forEach(registro -> {
                if(registro[1] != null){
                    for(int i = 1; i < registro.length; i++){
                        datos.addValue(Double.parseDouble(registro[i].toString()), registro[0].toString(), columnKeys[i-1]);
                    }
                }
            });
            LoadGrafic(datos);
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    //Task
    public void loadWeekGhrapic(){
        setLoad(true);
        Runnable run = () -> {
           List<Object[]> registros = getResultListOfProcedure();
            DefaultCategoryDataset datos = new DefaultCategoryDataset();
            String[] columsKeys = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};

            registros.forEach(registro -> {
                if(registro[1] != null){
                    for(int i = 1; i < registro.length; i++){
                        datos.addValue(Double.parseDouble(registro[i].toString()), registro[0].toString(), columsKeys[i - 1]);
                    }
                }
            });
            LoadGrafic(datos);
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    //Task
    public void loadMonthGrafic(){
        setLoad(true);
        Runnable run = () -> {
                List<Object[]> registros = getResultListOfProcedure();
            DefaultCategoryDataset datos = new DefaultCategoryDataset();

            registros.forEach(registro -> {
                if(registro[1] != null){
                    for(int i = 1; i < registro.length; i++){
                        datos.addValue(Double.parseDouble(registro[i].toString()), registro[0].toString(), String.valueOf(i));
                    }
                }
            });
            LoadGrafic(datos);
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    //Task
    public void loadYearGrafic(){
        setLoad(true);
        Runnable run = () -> {
            List<Object[]> registros = getResultListOfProcedure();
            DefaultCategoryDataset datos = new DefaultCategoryDataset();
            String[] columnsKeys = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", 
                                    "Septiembre", "Octubre", "Noviembre", "Diciembre"};

            registros.forEach(registro -> {
                if(registro[1] != null){
                    for(int i = 1; i < registro.length; i++){
                        datos.addValue(Double.parseDouble(registro[i].toString()), registro[0].toString(), columnsKeys[i - 1]);
                    }
                }
            });
            LoadGrafic(datos);
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    //Inside Task
    private List<Object[]> getResultListOfProcedure(){
        StoredProcedureQuery spq = Conection.createEntityManagerFactory().createEntityManager()
                .createStoredProcedureQuery("ProcedureComprasVentasGraficas")
                .registerStoredProcedureParameter("registros", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("tiempo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaInicio", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaFinal", Date.class, ParameterMode.IN);
        
        spq.setParameter("registros", Tipo.getSelectedIndex());
        spq.setParameter("tiempo", Tiempo.getSelectedIndex());
        spq.setParameter("FechaInicio", null);
        spq.setParameter("FechaFinal", null);
        
        return spq.getResultList();
    }
    
    private void LoadGrafic(DefaultCategoryDataset datos){
        JFreeChart grafico;
        
        grafico = ChartFactory.createLineChart("", "Tiempo", "Cantidad", datos, PlotOrientation.VERTICAL, true, true, true);
        grafico.setTitle(new TextTitle("Grafica de compras y ventas", new Font("Roboto Bold", Font.PLAIN, 24)));
        grafico.setTextAntiAlias(true);
        
        ChartPanel panel = new ChartPanel(grafico);
        
        Grafico.add(panel);
    }
}
