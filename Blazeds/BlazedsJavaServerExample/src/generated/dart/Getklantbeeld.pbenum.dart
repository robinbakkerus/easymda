///
//  Generated code. Do not modify.
//  source: Getklantbeeld.proto
///
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name

// ignore_for_file: UNDEFINED_SHOWN_NAME,UNUSED_SHOWN_NAME
import 'dart:core' as $core show int, dynamic, String, List, Map;
import 'package:protobuf/protobuf.dart' as $pb;

class GeslachtMsg extends $pb.ProtobufEnum {
  static const GeslachtMsg MAN = GeslachtMsg._(0, 'MAN');
  static const GeslachtMsg VROUW = GeslachtMsg._(1, 'VROUW');

  static const $core.List<GeslachtMsg> values = <GeslachtMsg> [
    MAN,
    VROUW,
  ];

  static final $core.Map<$core.int, GeslachtMsg> _byValue = $pb.ProtobufEnum.initByValue(values);
  static GeslachtMsg valueOf($core.int value) => _byValue[value];

  const GeslachtMsg._($core.int v, $core.String n) : super(v, n);
}

class PensioenAanspraakTypeMsg extends $pb.ProtobufEnum {
  static const PensioenAanspraakTypeMsg TYPE_A = PensioenAanspraakTypeMsg._(0, 'TYPE_A');
  static const PensioenAanspraakTypeMsg TYPE_B = PensioenAanspraakTypeMsg._(1, 'TYPE_B');

  static const $core.List<PensioenAanspraakTypeMsg> values = <PensioenAanspraakTypeMsg> [
    TYPE_A,
    TYPE_B,
  ];

  static final $core.Map<$core.int, PensioenAanspraakTypeMsg> _byValue = $pb.ProtobufEnum.initByValue(values);
  static PensioenAanspraakTypeMsg valueOf($core.int value) => _byValue[value];

  const PensioenAanspraakTypeMsg._($core.int v, $core.String n) : super(v, n);
}

