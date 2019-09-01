import 'dart:async';

import '../data/app_data.dart';
import '../events/app_events.dart';
import 'package:blazeds_flutter_grpc_example/service/klantbeeld_srv.dart';
import '../mock/klantbeeld_mock.dart';

class KlantbeeldServiceMock implements IKlantbeeldService {
  @override
  Future<void> getKlantbeeld(List<String> args) async {
    print("TODO mock started");

    // final name = args.isNotEmpty ? args[0] : 'world';

    try {
      AppData().klantbeeld = KlantbeeldMsgMock.build();
      AppEvents.fireKlantbeeldReady();
    } catch (e) {
      print('Caught error: $e');
    }
  }
}
