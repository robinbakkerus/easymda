part of form_fields;

abstract class BaseFormField extends PolymerElement {

  @observable@published
  Object value;
  
  @published@observable
  String validationMsg;
  @published@observable
  String label;
  @published@observable
  bool req = false;
  @published
  String styleClass = "defaultStyle";

  @observable
  String validationTooltip_;

  @published@observable
  String labelWidth;
  
  @published@observable
  String fieldWidth;
  
  @published@observable
  String placeholder = "Please fill ..";
  
  @published@observable
  bool editable = true;
  
  static int _radioGroupId = 0;
  
  bool get applyAuthorStyles => styleClass != null;
  
  BaseFormField.created(): super.created() {
    _setStyles();
  }

  void keyUp(KeyboardEvent evt) {
    validate();
  }

  void keyPress(KeyboardEvent evt) {
    var key = evt.keyCode;

    if (!_validKey(key)) {
      evt.preventDefault();
    }
  }

  bool _validKey(int key) => true;
  bool _validateData_(String data) => true;
  
  bool validate() {
    if (inputField.value == null) {
      return _validateData_(null);
    } else {
      return _validateData_(inputField.value.trim());
    }
  }

  void _updateWarning(bool ok) {
    validationTooltip_ = (ok) ? '' : makeValidationTooltip_;
    warning.classes.toggle('hidden', ok);  
  }
  
  String get makeValidationTooltip_ {
    return (validationMsg == null) ? _makeValidationMsg : validationMsg;
  }


  String get _makeValidationMsg {
    String result = "";
    if (req) {
      result += "This field is required";
    }
    result += " " + _maxmsg();
    result += " " + _minmsg();
    return result;
  }

  PolymerExpressions syntax = new MyPolymerExpressions();

  String _maxmsg() => '';
  String _minmsg() => '';

  // element selectors
  InputElement get inputField {
    if (shadowRoot != null) {
      return shadowRoot.querySelector("#idField");
    } else {
      return null;
    }
  }
  
  ImageElement get warning {
    if (shadowRoot != null) {
      return shadowRoot.querySelector("#idWarn");
    } else {
      return null;
    }
  }
  
  LabelElement get fieldLabel {
    if (shadowRoot != null) {
      return shadowRoot.querySelector("#idLabel");
    } else {
      return null;
    }
  }
  
  
  void _setStyles() {
    if (labelWidth != null) {
      fieldLabel.style.width = labelWidth;
    }

    if (fieldWidth != null && inputField != null) {
      inputField.style.width = fieldWidth;
    }
    
    if (!editable && inputField != null) {
      inputField.readOnly = true;
      inputField.classes.add("readonly");
    }
 }
}
