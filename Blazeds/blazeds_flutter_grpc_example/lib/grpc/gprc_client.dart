import 'dart:async';

import 'package:blazeds_flutter_grpc_example/grpc/Getklantbeeld.pbgrpc.dart';
import 'package:grpc/grpc.dart';
import '../data/app_data.dart';
import '../events/app_events.dart';

//import 'Getklantbeeld.pbenum.dart';
import 'Getklantbeeld.pb.dart';

class GprcClient {
  ClientChannel channel;
  GetKlantbeeldClient stub;

  GprcClient() {
    this.channel = ClientChannel('192.168.2.62',
        port: 50051,
        options:
            const ChannelOptions(credentials: ChannelCredentials.insecure()));

    this.stub = GetKlantbeeldClient(channel);
  }

  Future<void> getKlantbeeld(List<String> args) async {
    print("TODO 1");

    final name = args.isNotEmpty ? args[0] : 'world';

    try {
      final response =
          await stub.getKlantbeeld(SelectKlantbeeldMsg()..achternaam = name);
      print('GetKlantbeeld client received: ${response.achternaam}');
      AppData().klantbeeld = response;
      AppEvents.fireKlantbeeldReady();
    } catch (e) {
      print('Caught error: $e');
    }
    await channel.shutdown();
  }
}
