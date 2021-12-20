package viewer;
import java.lang.Math;


import formula.*;
import javafx.scene.chart.XYChart;

import java.util.*;

class FunctionList {
  private final List<PlottableFunction> functions = new ArrayList<>();

  private FunctionChart functionChart;
  private double lowerBound;
  private double upperBound;

  FunctionList(FunctionChart functionChart) {
    this.functionChart = functionChart;
    this.lowerBound = functionChart.getLowerBound();
    this.upperBound = functionChart.getUpperBound();

    //PlottableFunction function_constant = new PlottableFunction(new Constant(1), "f");
    //addFunctionAndItsDerivative(function_constant);

    //PlottableFunction function_variableX = new PlottableFunction(new VariableX(), "g");
    //addFunctionAndItsDerivative(function_variableX);

    //PlottableFunction function_addition = new PlottableFunction(new Addition(new VariableX(), new Constant(5)), "h");
    //addFunctionAndItsDerivative(function_addition);

    //PlottableFunction function_addition2 = new PlottableFunction(new Addition(new VariableX(), new VariableX()), "h2");
    //addFunctionAndItsDerivative(function_addition2);

    //PlottableFunction function_subtraction = new PlottableFunction(new Subtraction(new VariableX(), new Constant(5)), "u");
    //addFunctionAndItsDerivative(function_subtraction);

    //PlottableFunction function_multiplication = new PlottableFunction(new Multiplication(new VariableX(), new Constant(5)), "v");
    //addFunctionAndItsDerivative(function_multiplication);

    //PlottableFunction function_division = new PlottableFunction(new Division(new VariableX(), new Constant(5)), "w");
    //addFunctionAndItsDerivative(function_division);


    PlottableFunction function_division2 = new PlottableFunction(new Division(new Constant(-5), new Identity(new VariableX())), "w2");
    addFunctionAndItsDerivative(function_division2);

    PlottableFunction function_identity = new PlottableFunction(new Identity(new VariableX()), "i");
    addFunctionAndItsDerivative(function_identity);

    PlottableFunction function_opposite = new PlottableFunction(new Opposite(new Opposite(new Identity(new VariableX()))), "o");
    addFunctionAndItsDerivative(function_opposite);

    PlottableFunction function_opposite2 = new PlottableFunction(new Opposite(new Addition(new Constant(-4), new Constant(-5))), "o2");
    addFunctionAndItsDerivative(function_opposite2);

    //PlottableFunction function_exponential = new PlottableFunction(new Exponential(new VariableX()), "e");
    //addFunctionAndItsDerivative(function_exponential);

    //this.lowerBound = 1;
    //PlottableFunction function_logarithm = new PlottableFunction(new Logarithm(new VariableX()), "l");
    //addFunctionAndItsDerivative(function_logarithm);

    //PlottableFunction function_sine = new PlottableFunction(new Sine(new VariableX()), "s");
    //addFunctionAndItsDerivative(function_sine);

    //PlottableFunction function_cosine = new PlottableFunction(new Cosine(new VariableX()), "c");
    //addFunctionAndItsDerivative(function_cosine);

    PlottableFunction function_monomial = new PlottableFunction(new MonomialX(new VariableX(),2.0, 2), "c");
    addFunctionAndItsDerivative(function_monomial);

    PlottableFunction function_exponentiation = new PlottableFunction(new Exponentiation(new Constant(2), new Identity(new VariableX())), "c");
    addFunctionAndItsDerivative(function_exponentiation);

    Map map = new TreeMap<Integer, Double>();
    map.put(2, 3.5);
    map.put(3, 1.2);
    PlottableFunction function_polynomial = new PlottableFunction(new PolynomialX(new VariableX(), map), "c");
    addFunctionAndItsDerivative(function_polynomial);

  }

  void toggleFunction(PlottableFunction function) {
    if (function.isPlotted()){
      unplot(function);
    }
    else{
      plot(function);
    }
  }

  private void unplot(PlottableFunction function) {
    functionChart.removeSeries(function.toString());
    function.setPlotted(false);
  }

  List<PlottableFunction> getFunctions(){
    return functions;
  }

  private void plot(PlottableFunction function){
    XYChart.Series<Number, Number> series = function.getData(lowerBound, upperBound);
    series.setName(function.toString());
    functionChart.getData().add(series);
    function.setPlotted(true);
  }

  private void addFunctionsAndTheirDerivative(Collection<PlottableFunction> functions){
    for(PlottableFunction function: functions){
      addFunctionAndItsDerivative(function);
    }
  }

  private void addFunctionAndItsDerivative(PlottableFunction function){
    add(function);
    add(function.derivative());
  }

  private void add(PlottableFunction function) {
    functions.add(function);
  }

  void clear() {
    functionChart.getData().clear();
    for(PlottableFunction function: functions){
      function.setPlotted(false);
    }
  }
}
