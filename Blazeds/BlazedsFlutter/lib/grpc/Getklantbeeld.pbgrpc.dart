///
//  Generated code. Do not modify.
//  source: Getklantbeeld.proto
///
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name

import 'dart:async' as $async;

import 'package:grpc/service_api.dart' as $grpc;

import 'dart:core' as $core show int, String, List;

import 'Getklantbeeld.pb.dart';
export 'Getklantbeeld.pb.dart';

class GetKlantbeeldClient extends $grpc.Client {
  static final _$getKlantbeeld =
      $grpc.ClientMethod<SelectKlantbeeldMsg, KlantbeeldMsg>(
          '/jrb.grpc.GetKlantbeeld/getKlantbeeld',
          (SelectKlantbeeldMsg value) => value.writeToBuffer(),
          ($core.List<$core.int> value) => KlantbeeldMsg.fromBuffer(value));

  GetKlantbeeldClient($grpc.ClientChannel channel, {$grpc.CallOptions options})
      : super(channel, options: options);

  $grpc.ResponseFuture<KlantbeeldMsg> getKlantbeeld(SelectKlantbeeldMsg request,
      {$grpc.CallOptions options}) {
    final call = $createCall(
        _$getKlantbeeld, $async.Stream.fromIterable([request]),
        options: options);
    return $grpc.ResponseFuture(call);
  }
}

abstract class GetKlantbeeldServiceBase extends $grpc.Service {
  $core.String get $name => 'jrb.grpc.GetKlantbeeld';

  GetKlantbeeldServiceBase() {
    $addMethod($grpc.ServiceMethod<SelectKlantbeeldMsg, KlantbeeldMsg>(
        'getKlantbeeld',
        getKlantbeeld_Pre,
        false,
        false,
        ($core.List<$core.int> value) => SelectKlantbeeldMsg.fromBuffer(value),
        (KlantbeeldMsg value) => value.writeToBuffer()));
  }

  $async.Future<KlantbeeldMsg> getKlantbeeld_Pre(
      $grpc.ServiceCall call, $async.Future request) async {
    return getKlantbeeld(call, await request);
  }

  $async.Future<KlantbeeldMsg> getKlantbeeld(
      $grpc.ServiceCall call, SelectKlantbeeldMsg request);
}
