package gui2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class MBarChart {
	
	private final ObservableList<Data<String,Integer>> marks=FXCollections.observableArrayList();
	private XYChart.Series<String,Integer> data;

	@FXML private CategoryAxis categoryAxis;
	@FXML private NumberAxis numberAxis;
	@FXML private BarChart barChart;
	
	
	@FXML public void initialize(){
		marks.addAll(
				new Data<String,Integer>(Double.toString(3.0),0),
				new Data<String,Integer>(Double.toString(3.5),0),
				new Data<String,Integer>(Double.toString(4.0),0),
				new Data<String,Integer>(Double.toString(4.5),0),
				new Data<String,Integer>(Double.toString(5.0),0)
				);
		data=new XYChart.Series<String,Integer>(marks);
		barChart.getData().add(data);	
	}
	
	public void addMark(double mark){
		for(Data<String,Integer> d:marks){
			if (d.getXValue().equals(Double.toString(mark))){
				d.setYValue(d.getYValue()+1);
				if (numberAxis.getUpperBound()==(double)d.getYValue()){
					numberAxis.setUpperBound(numberAxis.getUpperBound()+1.0);
				}
				break;
			}
		}
		
	}
	
	public void removeMark(double mark){
		for (Data<String,Integer> d:marks){
			if (d.getXValue().equals(Double.toString(mark))){
				if (d.getYValue()>0){
					d.setYValue(d.getYValue()-1);
				}
				if (numberAxis.getUpperBound()>10.0){
					numberAxis.setUpperBound(numberAxis.getUpperBound()-1.0);
				}
				break;
			}
		}
	}
}
