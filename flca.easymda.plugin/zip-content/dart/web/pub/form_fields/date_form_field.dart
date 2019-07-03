part of form_fields;

@CustomTag('date-form-field')
class DateFormField extends BaseFormField {

  @observable@published
  DateTime value;
  @published
  DateTime min;
  @published
  DateTime max;

  DateFormField.created(): super.created() {}

  @override
  void keyUp(KeyboardEvent evt) {
    validate();
  }

  @override
  bool _validKey(int key) => true; 
      
  @override
  bool _validateData_(String data) {
    bool b = !data.isEmpty;
    if (b) value = DateConversions.stringToDate(data);
    _updateWarning(b);
    return b;
  }

  @override 
  String _maxmsg() => (min==null) ? "" : "must be greater than $min";
  
  @override
  String _minmsg() => (max==null) ? "" : "must be smaller than $max";

}

