library view.main_view;

import '../<%=appname%>_library.dart';

@CustomTag('main-view')
class MainView extends PolymerElement {

  static MainView _instance;
  
  MainView.created() : super.created() {
    var mainctrl = new MainController();
    _instance = this;
  }

  static MainView instance() {
    print(_instance);
    return _instance;
  }
  
  void showContent(element) {
    var c = this.shadowRoot.querySelector('#maincontent');
    c.children.clear();
    c.children.add(element);
  }
}

