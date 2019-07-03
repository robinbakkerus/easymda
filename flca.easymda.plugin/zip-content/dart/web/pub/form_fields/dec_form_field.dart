part of form_fields;

@CustomTag('dec-form-field')
class DecFormField extends BaseFormField {

  @observable@published
  double value;
  @published
  double min;
  @published
  int max;
  @published
  int precision = 2;

  DecFormField.created(): super.created() {
    syntax = new MyPolymerExpressions(precision : precision);
  }

  @override
  void keyUp(KeyboardEvent evt) {
    validate();
  }

  @override
  bool _validKey(int key) => (key >= 48 && key <= 57) || key == 46; 
      
  @override
  bool _validateData_(String data) {
    bool b = (req) ? (data!=null && !data.isEmpty) : true;
    if (b) {
      value = double.parse(data);
      if (min != null) b = b && value >= min;
      if (max != null) b = b && value <= max;
    }
    
    _updateWarning(b);
    return b;
  }

  @override 
  String _maxmsg() => (min==null) ? "" : "must be greater than $min";
  
  @override
  String _minmsg() => (max==null) ? "" : "must be smaller than $max";

}

