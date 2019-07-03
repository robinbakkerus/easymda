part of form_fields;

@CustomTag('string-form-field')
class StringFormField extends BaseFormField {

  @observable@published
  String value;

  StringFormField.created(): super.created() {}

  @override
  void keyUp(KeyboardEvent evt) {  validate(); }

  @override
  bool _validKey(int key) => true; 
      
  @override
  bool _validateData_(String data) {
    bool b = !data.isEmpty;
    _updateWarning(b);
    return b;
  }

  @override String _maxmsg() => '';
  @override String _minmsg() => '';

}

