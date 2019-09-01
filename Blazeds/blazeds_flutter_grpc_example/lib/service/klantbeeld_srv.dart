import 'klantbeeld_srv_mock_impl.dart';

abstract class IKlantbeeldService {

  Future<void> getKlantbeeld(List<String> args) async {}

  factory IKlantbeeldService() {
    return KlantbeeldServiceMock();
  }
}