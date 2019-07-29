import 'package:event_bus/event_bus.dart';

class KlantbeeldSelectedEvent{}

typedef void OnKlantbeeldReadyFunc(KlantbeeldSelectedEvent event);

class AppEvents {
  static final EventBus _sEventBus = new EventBus();

  // Only needed if clients want all EventBus functionality.
  static EventBus ebus() => _sEventBus;

  /*
  * The methods below are just convenience shortcuts to make it easier for the client to use.
  */
  static void fireKlantbeeldReady() => _sEventBus.fire(new KlantbeeldSelectedEvent());  

  static void onKlantbeeldSelected(OnKlantbeeldReadyFunc func) =>
      _sEventBus.on<KlantbeeldSelectedEvent>().listen((event) => func(event));
}

