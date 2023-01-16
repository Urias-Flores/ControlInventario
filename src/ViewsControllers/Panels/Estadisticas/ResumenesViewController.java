package ViewsControllers.Panels.Estadisticas;

import Resource.Conection;
import java.awt.Color;
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
        if(registros.get(0)[1] != null){
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
        }
        
        grafico = ChartFactory.createLineChart("Mi grafica", "Tiempo", "Dinero", datos, PlotOrientation.VERTICAL, true, true, true);
        grafico.setTitle(new TextTitle("Grafica de compras y ventas", new Font("Roboto Bold", Font.PLAIN, 24)));
        grafico.setTextAntiAlias(true);
        
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
        spq.setParameter("FechaInicio", null);
        spq.setParameter("FechaFinal", null);
        
        List<Object[]> registros = spq.getResultList();
        JFreeChart grafico;
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        if(registros.get(0)[1] != null){
            registros.forEach(registro -> {
                datos.addValue(Double.parseDouble(registro[1].toString()), registro[0].toString(), "Lunes");
                datos.addValue(Double.parseDouble(registro[2].toString()), registro[0].toString(), "Martes");
                datos.addValue(Double.parseDouble(registro[3].toString()), registro[0].toString(), "Miercoles");
                datos.addValue(Double.parseDouble(registro[4].toString()), registro[0].toString(), "Jueves");
                datos.addValue(Double.parseDouble(registro[5].toString()), registro[0].toString(), "Viernes");
                datos.addValue(Double.parseDouble(registro[6].toString()), registro[0].toString(), "Sabado");
            });
        }
        grafico = ChartFactory.createLineChart("Mi grafica", "Tiempo", "Dinero", datos, PlotOrientation.VERTICAL, true, true, true);
        grafico.setTitle(new TextTitle("Grafica de compras y ventas", new Font("Roboto Bold", Font.PLAIN, 24)));
        grafico.setTextAntiAlias(true);
        
        ChartPanel panel = new ChartPanel(grafico);
        
        Grafico.add(panel);
    }
    
    public void cargarGraficoMes(){
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
        if(registros.get(0)[1] != null){
            registros.forEach(registro -> {
                datos.addValue(Double.parseDouble(registro[1].toString()), registro[0].toString(), "1");
                datos.addValue(Double.parseDouble(registro[2].toString()), registro[0].toString(), "2");
                datos.addValue(Double.parseDouble(registro[3].toString()), registro[0].toString(), "2");
                datos.addValue(Double.parseDouble(registro[4].toString()), registro[0].toString(), "4");
                datos.addValue(Double.parseDouble(registro[5].toString()), registro[0].toString(), "5");
                datos.addValue(Double.parseDouble(registro[6].toString()), registro[0].toString(), "6");
                datos.addValue(Double.parseDouble(registro[7].toString()), registro[0].toString(), "7");
                datos.addValue(Double.parseDouble(registro[8].toString()), registro[0].toString(), "8");
                datos.addValue(Double.parseDouble(registro[9].toString()), registro[0].toString(), "9");
                datos.addValue(Double.parseDouble(registro[10].toString()), registro[0].toString(), "10");
                datos.addValue(Double.parseDouble(registro[11].toString()), registro[0].toString(), "11");
                datos.addValue(Double.parseDouble(registro[12].toString()), registro[0].toString(), "12");
                datos.addValue(Double.parseDouble(registro[13].toString()), registro[0].toString(), "13");
                datos.addValue(Double.parseDouble(registro[14].toString()), registro[0].toString(), "14");
                datos.addValue(Double.parseDouble(registro[15].toString()), registro[0].toString(), "15");
                datos.addValue(Double.parseDouble(registro[16].toString()), registro[0].toString(), "16");
                datos.addValue(Double.parseDouble(registro[17].toString()), registro[0].toString(), "17");
                datos.addValue(Double.parseDouble(registro[18].toString()), registro[0].toString(), "18");
                datos.addValue(Double.parseDouble(registro[19].toString()), registro[0].toString(), "19");
                datos.addValue(Double.parseDouble(registro[20].toString()), registro[0].toString(), "20");
                datos.addValue(Double.parseDouble(registro[21].toString()), registro[0].toString(), "21");
                datos.addValue(Double.parseDouble(registro[22].toString()), registro[0].toString(), "22");
                datos.addValue(Double.parseDouble(registro[23].toString()), registro[0].toString(), "23");
                datos.addValue(Double.parseDouble(registro[24].toString()), registro[0].toString(), "24");
                datos.addValue(Double.parseDouble(registro[25].toString()), registro[0].toString(), "25");
                datos.addValue(Double.parseDouble(registro[26].toString()), registro[0].toString(), "26");
                datos.addValue(Double.parseDouble(registro[27].toString()), registro[0].toString(), "27");
                datos.addValue(Double.parseDouble(registro[28].toString()), registro[0].toString(), "28");
                datos.addValue(Double.parseDouble(registro[29].toString()), registro[0].toString(), "29");
                datos.addValue(Double.parseDouble(registro[30].toString()), registro[0].toString(), "30");
                datos.addValue(Double.parseDouble(registro[31].toString()), registro[0].toString(), "31");
            });
        }
        grafico = ChartFactory.createLineChart("Mi grafica", "Tiempo", "Dinero", datos, PlotOrientation.VERTICAL, true, true, true);
        grafico.setTitle(new TextTitle("Grafica de compras y ventas", new Font("Roboto Bold", Font.PLAIN, 24)));
        grafico.setTextAntiAlias(true);
        
        ChartPanel panel = new ChartPanel(grafico);
        
        Grafico.add(panel);
    }
    
    public void cargarGraficoAnio(){
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
        if(registros.get(0)[1] != null){
            registros.forEach(registro -> {
                datos.addValue(Double.parseDouble(registro[1].toString()), registro[0].toString(), "Enero");
                datos.addValue(Double.parseDouble(registro[2].toString()), registro[0].toString(), "Febrero");
                datos.addValue(Double.parseDouble(registro[3].toString()), registro[0].toString(), "Marzo");
                datos.addValue(Double.parseDouble(registro[4].toString()), registro[0].toString(), "Abril");
                datos.addValue(Double.parseDouble(registro[5].toString()), registro[0].toString(), "Mayo");
                datos.addValue(Double.parseDouble(registro[6].toString()), registro[0].toString(), "Junio");
                datos.addValue(Double.parseDouble(registro[7].toString()), registro[0].toString(), "Julio");
                datos.addValue(Double.parseDouble(registro[8].toString()), registro[0].toString(), "Agosto");
                datos.addValue(Double.parseDouble(registro[9].toString()), registro[0].toString(), "Septiembre");
                datos.addValue(Double.parseDouble(registro[10].toString()), registro[0].toString(), "Octubre");
                datos.addValue(Double.parseDouble(registro[11].toString()), registro[0].toString(), "Noviembre");
                datos.addValue(Double.parseDouble(registro[12].toString()), registro[0].toString(), "Diciembre");
            });
        }
        grafico = ChartFactory.createLineChart("", "Tiempo", "Cantidad", datos, PlotOrientation.VERTICAL, true, true, true);
        grafico.setTitle(new TextTitle("Grafica de compras y ventas", new Font("Roboto Bold", Font.PLAIN, 24)));
        grafico.setTextAntiAlias(true);
        
        ChartPanel panel = new ChartPanel(grafico);
        
        Grafico.add(panel);
    }
}
