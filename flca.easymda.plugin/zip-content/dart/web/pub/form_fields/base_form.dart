part of form_fields; 

abstract class AbstractForm  {

  Function submitHandler;

  //abstract methods
  Object updateModel();
  void updateView();
  List<String> get fieldIds;
  ShadowRoot get formRoot;

  void submitForm(Event e) {
    e.preventDefault();
    bool ok = fieldIds.every( (key) => validateField(key));
    if (ok) {
      if (submitHandler != null) submitHandler(updateModel());
    } else {
      //TODO ? message?
    }
  }
  

  bool validateField(key) {
     BaseFormField fld = formRoot.querySelector(key);
     if (fld != null) {
       return fld.validate();
     } else {
       print("no field found for $key");
       return true;
     }
  }
  
  Object getValue(key) {
    BaseFormField fld = formRoot.querySelector(key);
    if (fld != null) {
      return fld.value;
    } else {
      print("no field found for $key");
      return null;
    }
  }
  
  void setValue(key, val) {
    BaseFormField fld = formRoot.querySelector(key);
    if (fld != null) {
      fld.value = val;
    } else {
      print("no field found for $key");
    }
  }
  
  void setCombobox(key, val, values) {
    ComboFormField fld = formRoot.querySelector(key);
    if (val != null) {
      fld.selectedIndex = values.indexOf(val) + 1;
    } 
  }
  
  int getSelectedIndex(key) {
    ComboFormField fld = formRoot.querySelector(key);
    return fld.selectedIndex - 1;
  }
  
}