import 'package:blazeds_flutter_grpc_example/grpc/Getklantbeeld.pbgrpc.dart';

const VOORNAAM = 'robin';

class KlantbeeldMsgMock {
  static KlantbeeldMsg build() {
    KlantbeeldMsg r = KlantbeeldMsg();
    r.voornaam = VOORNAAM;
    return r;
  }
}