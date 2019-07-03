part of form_fields;

@CustomTag('bool-form-field')
class BoolFormField extends BaseFormField {

  @observable@published
  bool value;

  BoolFormField.created(): super.created() {}

  @override
  void keyUp(KeyboardEvent evt) {
    validate();
  }

  @override
  bool _validKey(int key) => true; 
      
  @override
  bool _validateData_(String data) {
    bool b = !data.isEmpty;
    return b;
  }
  
  @override 
  String _maxmsg() => '';
  
  @override
  String _minmsg() => '';

}

