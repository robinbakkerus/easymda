import '../grpc/Getklantbeeld.pb.dart';

class AppData {
  static final AppData _instance = new AppData._internal();
  factory AppData() => _instance;
  KlantbeeldMsg klantbeeld;

  AppData._internal() {
  }
  
}