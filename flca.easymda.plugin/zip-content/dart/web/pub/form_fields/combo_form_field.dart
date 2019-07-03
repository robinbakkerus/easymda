part of form_fields;

@CustomTag('combo-form-field')
class ComboFormField extends BaseFormField {

  @observable@published
  Object value;

  @observable@published
  String prompt;

  Map _items;
  
  @observable@published
  Map get items => _items;
      set items(aItems) {_items = aItems; _addComboItems();}


  SelectElement _cb;
  int get selectedIndex => _cb.selectedIndex;
      set selectedIndex(int val) => _cb.selectedIndex = val;
  
  
  ComboFormField.created(): super.created() {}

  SpanElement get _radioDiv {
    if (shadowRoot != null) {
      return shadowRoot.querySelector("#idComboDiv");
    } else {
      return null;
    }
  }
 
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
    bool b = (req) ? (data != null && !data.isEmpty) : true;
    if (b) value = data;
    _updateWarning(b);
    return b;
  }

  void _addComboItems() {
    _cb = new SelectElement();
    _cb.onChange.listen((e) => _comboClicked(e));
    if (prompt != null) {
      _addItem(_cb, prompt, '');
    } else {
      _addItem(_cb, '', '');
    }
    
    if (_items != null) {
      _items.forEach((k, v) => _addItem(_cb, k, v));
    }
    
    if (_radioDiv != null) {
      _radioDiv.children.add(_cb);
    }
  }

  void _addItem(SelectElement cb, String key, Object optValue) {
    OptionElement opt = new OptionElement();
    opt.text = key;
    opt.value = optValue.toString();
    opt.selected = optValue == value;
    cb.children.add(opt);
  }

  void _comboClicked(Event evt) {
    SelectElement cb = evt.currentTarget;
    value = cb.value;
    validate();
  }

  bool _isChecked(v) => v == value;

}
