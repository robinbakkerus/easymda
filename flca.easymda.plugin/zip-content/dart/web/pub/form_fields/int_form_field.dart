part of form_fields;

@CustomTag('int-form-field')
class IntFormField extends BaseFormField {

  @observable@published
  int value;
  @published
  int min;
  @published
  int max;

  IntFormField.created(): super.created() {}

  @override
  bool _validKey(int key) => (key >= 48 && key <= 57); 

  @override
  void keyUp(KeyboardEvent evt) {
    validate();
  }

  @override
  bool _validateData_(String data) {
    bool b = (req) ? (data!=null && !data.isEmpty) : true;

    if (b) {
      value = int.parse(data);
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


