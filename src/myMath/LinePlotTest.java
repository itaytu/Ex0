package myMath;

import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {
    public LinePlotTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        DataTable data2 = new DataTable(Double.class, Double.class);
        Polynom p = new Polynom ("0.2x^4-1.5x^3+3.0x^2-x-5");
        Polynom pd = new Polynom (p.derivative());
        double startp = -2;
        double endp = 6;
        double eps =0.01;
        for (double x = startp;x <= endp; x+=0.25) {
        	if (pd.f(x)>0 && pd.f(x)<0.5 ) {
        		double y = p.f(x);
        		System.out.println("the x axis is: " + x  +" the y axis is: "+ y);
                data2.add(x, y);
        	}else {
        		double y = p.f(x);
                data.add(x, y);
        	}
        }
        XYPlot plot = new XYPlot(data,data2);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getPointRenderers(data2).get(0).setColor(color.RED);
        plot.getLineRenderers(data).get(0).setColor(color);
        System.out.println("The area calculated is: " + area(p, startp, endp, eps));
    }
    
	public double area(Polynom p, double x0, double x1, double eps) {
		double sum = 0;
		for (double i = x0; i <= x1; i+=eps) {
			if(p.f(i)<0)
			sum += p.f(i)*eps;
		}
		return Math.abs(sum);
	}

    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
    }
}