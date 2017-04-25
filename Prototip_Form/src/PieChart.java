import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
 
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYBarRenderer;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.time.Year;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.VerticalAlignment;
 
 
public class PieChart extends ApplicationFrame {
 
    /**
     * ������ ����� ����� � ��������
     * @param title ��������� ����
     */
    public PieChart(String title) {
        super(title);
        // ������ ����� ������
        JFreeChart chart = createChart(createDataset());
        // �� ������
        ChartPanel chartPanel = new ChartPanel(chart);
        // � ��������� 450*450
        chartPanel.setPreferredSize(new Dimension(450, 450));
        // � ���������� ���� ����������
        JScrollPane sp = new JScrollPane(chartPanel);
        sp.setPreferredSize(new Dimension(500, 500));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setContentPane(sp);
    }
 
    /**
     * ��������� Set ������� ��� ���������� �������
     * @return ������ ��� ����������
     */
    private static TableXYDataset createDataset() {
        // ���� ������
        TimeTableXYDataset dataset = new TimeTableXYDataset();
        dataset.add(new Year(2002), 50, "Blue");
        dataset.add(new Year(2003), 50, "Blue");
        dataset.add(new Year(2002), 10, "Red");
        dataset.add(new Year(2003), 50, "Red");
        return dataset;
    }
 
    /**
     * ������ ����� ������ �� ������
     * @param dataset ������ ��� ����������
     * @return ������
     */
    private static JFreeChart createChart(TableXYDataset dataset) {
 
        // OX - ��� �������
        // ������ �������� ���
        DateAxis domainAxis = new DateAxis("Year");
        // ���������� ��������� ������
        domainAxis.setPositiveArrowVisible(true);
        // ������ ������ �� �������
        domainAxis.setUpperMargin(0.2);
 
        // OY - ��� �������
        // ����� �������� ���
        NumberAxis rangeAxis = new NumberAxis("Color");
        // ����� �������� �������
        rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        rangeAxis.setTickUnit(new NumberTickUnit(200));
        // ���������� ��������� �����
        rangeAxis.setPositiveArrowVisible(true);
 
 
        // Render
        // ������� ��������� (�� ���� ��� ����� ���������) ������
        // 0.02 - ���������� ����� ����������
        StackedXYBarRenderer renderer = new StackedXYBarRenderer(0.02);
        // ��� �����
        renderer.setDrawBarOutline(false);
        // ����� ��� ������� �������� ������
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesPaint(1, Color.red);
        // ����� ������ � ����� ���������
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0} : {1} = {2} tonnes", new SimpleDateFormat("yyyy"), new DecimalFormat("#,##0")));
        renderer.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        renderer.setSeriesItemLabelGenerator(1, new StandardXYItemLabelGenerator());
        // ������ � �������
        renderer.setSeriesItemLabelsVisible(0, true);
        renderer.setSeriesItemLabelsVisible(1, true);
        // � ��������� � �����
        renderer.setSeriesItemLabelFont(0, new Font("Serif", Font.BOLD, 10));
        renderer.setSeriesItemLabelFont(1, new Font("Serif", Font.BOLD, 10));
 
        // Plot
        // ������� ������� ���������
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        // �����������
        plot.setBackgroundPaint(Color.white);
        // ����������� �����
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        // ������ �� ����
        plot.setAxisOffset(new RectangleInsets(0D, 0D, 10D, 10D));
        plot.setOutlinePaint(null);
 
        // Chart
        // ������� ����� ������
        JFreeChart chart = new JFreeChart(plot);
        // �����������
        chart.setBackgroundPaint(Color.white);
        // ���������� ������� � ������� ������ ����
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setVerticalAlignment(VerticalAlignment.TOP);
 
        return chart;
    }
 
}