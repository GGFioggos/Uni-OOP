import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class Visualization extends JFrame {

	private Registry registry;
	private VisualizationImageServer visualization;
	
	private JTextField networkDiameter;

	public Visualization(Registry registry) {

		this.registry = registry;

		Graph graph = new SparseGraph<>();

		for (Suspect suspect : registry.getAllSuspects()) {
			graph.addVertex(suspect.getCodeName());
		}

		int counter = 0;
		for (Suspect suspect : registry.getAllSuspects()) {
			for (Suspect suspect2 : suspect.getPartners()) {
				graph.addEdge(counter, suspect.getCodeName(), suspect2.getCodeName());
				counter++;
			}
		}

		visualization = new VisualizationImageServer<>(new CircleLayout<>(graph), new Dimension(250, 250));
		visualization.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		
		Double diameter = DistanceStatistics.diameter(graph);
		networkDiameter = new JTextField("Diameter: " + diameter);
		
		this.getContentPane().add(visualization);
		this.add(networkDiameter,BorderLayout.PAGE_END);
		
		this.setVisible(true);
		this.setTitle("Network");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
