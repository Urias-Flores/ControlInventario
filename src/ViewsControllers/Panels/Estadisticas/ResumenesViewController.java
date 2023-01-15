package ViewsControllers.Panels.Estadisticas;

import Resource.Conection;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class ResumenesViewController {
    private JComboBox Tipo;
    private JComboBox Tiempo;
    private JPanel Grafico;

    public ResumenesViewController(JComboBox Tipo, JComboBox Tiempo, JPanel Grafico) {
        this.Tipo = Tipo;
        this.Tiempo = Tiempo;
        this.Grafico = Grafico;
    }
    
    public void cargarGraficoHoy(){
        StoredProcedureQuery spq = Conection.CreateEntityManager().createEntityManager()
                .createStoredProcedureQuery("ProcedureComprasVentasGraficas")
                .registerStoredProcedureParameter("registros", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("tiempo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaInicio", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaFinal", Date.class, ParameterMode.IN);
        
        spq.setParameter("registros", Tipo.getSelectedIndex());
        spq.setParameter("tiempo", Tiempo.getSelectedIndex());
        spq.setParameter("FechaInicio", null);
        spq.setParameter("FechaFinal", null);
        
        List<Object[]> registros = spq.getResultList();
        JFreeChart grafico;
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        registros.forEach(registro -> {
            datos.addValue(Double.parseDouble(registro[1].toString()), registro[0].toString(), "6 am");
            datos.addValue(Double.parseDouble(registro[2].toString()), registro[0].toString(), "7 am");
            datos.addValue(Double.parseDouble(registro[3].toString()), registro[0].toString(), "8 am");
            datos.addValue(Double.parseDouble(registro[4].toString()), registro[0].toString(), "9 am");
            datos.addValue(Double.parseDouble(registro[5].toString()), registro[0].toString(), "10 am");
            datos.addValue(Double.parseDouble(registro[6].toString()), registro[0].toString(), "11 am");
            datos.addValue(Double.parseDouble(registro[6].toString()), registro[0].toString(), "12 m");
            datos.addValue(Double.parseDouble(registro[8].toString()), registro[0].toString(), "1 pm");
            datos.addValue(Double.parseDouble(registro[9].toString()), registro[0].toString(), "2 pm");
            datos.addValue(Double.parseDouble(registro[10].toString()), registro[0].toString(), "3 pm");
            datos.addValue(Double.parseDouble(registro[11].toString()), registro[0].toString(), "4 pm");
            datos.addValue(Double.parseDouble(registro[12].toString()), registro[0].toString(), "5 pm");
            datos.addValue(Double.parseDouble(registro[13].toString()), registro[0].toString(), "6 pm");
        });
        
        grafico = ChartFactory.createLineChart("Mi grafica", "Tiempo", "Dinero", datos, PlotOrientation.VERTICAL, true, true, true);
        grafico.setTitle(new TextTitle("Grafica de compras y ventas", new Font("Roboto Bold", Font.PLAIN, 24)));
        
        ChartPanel panel = new ChartPanel(grafico);
        
        Grafico.add(panel);
    }
    
    public void cargarGraficoSemana(){
        StoredProcedureQuery spq = Conection.CreateEntityManager().createEntityManager()
                .createStoredProcedureQuery("ProcedureComprasVentasGraficas")
                .registerStoredProcedureParameter("registros", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("tiempo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaInicio", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaFinal", Date.class, ParameterMode.IN);
        
        spq.setParameter("registros", Tipo.getSelectedIndex());
        spq.setParameter("tiempo", Tiempo.getSelectedIndex());
        List<Object[]> registros = spq.getResultList();
        JFreeChart grafico;
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        
        registros.forEach(registro -> {
            datos.addValue(Double.parseDouble(registro[1].toString()), registro[0].toString(), "Lunes");
            datos.addValue(Double.parseDouble(registro[2].toString()), registro[0].toString(), "Martes");
            datos.addValue(Double.parseDouble(registro[3].toString()), registro[0].toString(), "Miercoles");
            datos.addValue(Double.parseDouble(registro[4].toString()), registro[0].toString(), "Jueves");
            datos.addValue(Double.parseDouble(registro[5].toString()), registro[0].toString(), "Viernes");
            datos.addValue(Double.parseDouble(registro[6].toString()), registro[0].toString(), "Sabado");
        });
        
        grafico = ChartFactory.createLineChart("Mi grafica", "Tiempo", "Dinero", datos, PlotOrientation.VERTICAL, true, true, true);
        grafico.setTitle(new TextTitle("Grafica de compras y ventas", new Font("Roboto Bold", Font.PLAIN, 24)));
        
        ChartPanel panel = new ChartPanel(grafico);
        
        Grafico.add(panel);
    }
}
