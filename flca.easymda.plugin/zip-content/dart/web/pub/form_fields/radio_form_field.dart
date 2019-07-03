part of form_fields;

@CustomTag('radio-form-field')
class RadioFormField extends BaseFormField {

  @observable@published
  String value;

  Map _items;
  @observable@published
  Map get items => _items;
  @observable@published
  void set items(aItems) {
    _items = aItems;
    _addRadioButtons();
  }

  RadioFormField.created(): super.created() {}

  SpanElement get _radioDiv => shadowRoot.querySelector("#idRadioDiv");

  @override
  bool validate() {
    if (value != null) {
      return _validateData_(value);
    } else {
      return _validateData_(null);
    }
  }

  @override
  bool _validateData_(String data) {
    bool b = (req) ? data != null : true;
    if (b) value = data;
    _updateWarning(b);
    return b;
  }

  void _addRadioButtons() {
    BaseFormField._radioGroupId += 1;
    _items.forEach((k, v) => _addRadio(k, v));
  }

  void _addRadio(k, v) {
    RadioButtonInputElement rb = new RadioButtonInputElement();
    rb.name = "radioGroup" + "${BaseFormField._radioGroupId}";
    rb.value = v;
    rb.checked = _isChecked(v);
    rb.onChange.listen((e) => _radioClicked(e));
    _radioDiv.children.add(rb);

    LabelElement lbl = new LabelElement();
    lbl.text = k;
    int w = lbl.text.length * 8;
    lbl.style.width = "${w}px";
    _radioDiv.children.add(lbl);
  }

  void _radioClicked(Event evt) {
    InputElement rb = evt.currentTarget;
    value = rb.value;
    validate();
  }
  
  bool _isChecked(v) => v == value;

}
