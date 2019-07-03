part of form_fields;


class MyPolymerExpressions extends PolymerExpressions {
  MyPolymerExpressions({int precision:2}): super(globals: {
        'intToString': (int input) => '$input',
        'doubleToString': (double input) => doubleToString(input, precision),
        'dateToString' : (DateTime input) => DateConversions.dateToString(input),
        'boolToString' : (bool input) => (input) ? 'true' : 'false',
      });

//  @override
//  prepareBinding(String path, name, node) => Polymer.prepareBinding(path, name, node, super.prepareBinding);
  
  static String doubleToString(double value, int precision) {
    return value.toStringAsFixed(precision);
  }
 
}

class DateConversions {

  static String dateToString(DateTime value) {
    var formatter = new DateFormat('yyyy-MM-dd');
    String formatted = formatter.format(value);
    return formatted;
  }
 
  static DateTime intToDate(value) {
    return new DateTime.fromMillisecondsSinceEpoch(value);
  }
  
  static DateTime stringToDate(String value) {
    return new DateTime.now();
  }
  
}