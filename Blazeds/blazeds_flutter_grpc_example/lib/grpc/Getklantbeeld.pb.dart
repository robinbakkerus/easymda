///
//  Generated code. Do not modify.
//  source: Getklantbeeld.proto
///
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name

import 'dart:core' as $core show bool, Deprecated, double, int, List, Map, override, String;

import 'package:fixnum/fixnum.dart';
import 'package:protobuf/protobuf.dart' as $pb;

import 'Getklantbeeld.pbenum.dart';

export 'Getklantbeeld.pbenum.dart';

class KlantbeeldMsg extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('KlantbeeldMsg', package: const $pb.PackageName('jrb.grpc'))
    ..a<$core.int>(1, 'klantId', $pb.PbFieldType.O3)
    ..aOS(2, 'voornaam')
    ..aOS(3, 'achternaam')
    ..aInt64(4, 'geboorteDatum')
    ..e<GeslachtMsg>(5, 'geslacht', $pb.PbFieldType.OE, GeslachtMsg.MAN, GeslachtMsg.valueOf, GeslachtMsg.values)
    ..aOS(6, 'saldo')
    ..a<AdresMsg>(7, 'adres', $pb.PbFieldType.OM, AdresMsg.getDefault, AdresMsg.create)
    ..pc<PensioenAanspraakMsg>(8, 'aanspraken', $pb.PbFieldType.PM,PensioenAanspraakMsg.create)
    ..hasRequiredFields = false
  ;

  KlantbeeldMsg() : super();
  KlantbeeldMsg.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) : super.fromBuffer(i, r);
  KlantbeeldMsg.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) : super.fromJson(i, r);
  KlantbeeldMsg clone() => KlantbeeldMsg()..mergeFromMessage(this);
  KlantbeeldMsg copyWith(void Function(KlantbeeldMsg) updates) => super.copyWith((message) => updates(message as KlantbeeldMsg));
  $pb.BuilderInfo get info_ => _i;
  static KlantbeeldMsg create() => KlantbeeldMsg();
  KlantbeeldMsg createEmptyInstance() => create();
  static $pb.PbList<KlantbeeldMsg> createRepeated() => $pb.PbList<KlantbeeldMsg>();
  static KlantbeeldMsg getDefault() => _defaultInstance ??= create()..freeze();
  static KlantbeeldMsg _defaultInstance;

  $core.int get klantId => $_get(0, 0);
  set klantId($core.int v) { $_setSignedInt32(0, v); }
  $core.bool hasKlantId() => $_has(0);
  void clearKlantId() => clearField(1);

  $core.String get voornaam => $_getS(1, '');
  set voornaam($core.String v) { $_setString(1, v); }
  $core.bool hasVoornaam() => $_has(1);
  void clearVoornaam() => clearField(2);

  $core.String get achternaam => $_getS(2, '');
  set achternaam($core.String v) { $_setString(2, v); }
  $core.bool hasAchternaam() => $_has(2);
  void clearAchternaam() => clearField(3);

  Int64 get geboorteDatum => $_getI64(3);
  set geboorteDatum(Int64 v) { $_setInt64(3, v); }
  $core.bool hasGeboorteDatum() => $_has(3);
  void clearGeboorteDatum() => clearField(4);

  GeslachtMsg get geslacht => $_getN(4);
  set geslacht(GeslachtMsg v) { setField(5, v); }
  $core.bool hasGeslacht() => $_has(4);
  void clearGeslacht() => clearField(5);

  $core.String get saldo => $_getS(5, '');
  set saldo($core.String v) { $_setString(5, v); }
  $core.bool hasSaldo() => $_has(5);
  void clearSaldo() => clearField(6);

  AdresMsg get adres => $_getN(6);
  set adres(AdresMsg v) { setField(7, v); }
  $core.bool hasAdres() => $_has(6);
  void clearAdres() => clearField(7);

  $core.List<PensioenAanspraakMsg> get aanspraken => $_getList(7);
}

class SelectKlantbeeldMsg extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('SelectKlantbeeldMsg', package: const $pb.PackageName('jrb.grpc'))
    ..a<$core.int>(1, 'klantId', $pb.PbFieldType.O3)
    ..aOS(2, 'achternaam')
    ..hasRequiredFields = false
  ;

  SelectKlantbeeldMsg() : super();
  SelectKlantbeeldMsg.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) : super.fromBuffer(i, r);
  SelectKlantbeeldMsg.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) : super.fromJson(i, r);
  SelectKlantbeeldMsg clone() => SelectKlantbeeldMsg()..mergeFromMessage(this);
  SelectKlantbeeldMsg copyWith(void Function(SelectKlantbeeldMsg) updates) => super.copyWith((message) => updates(message as SelectKlantbeeldMsg));
  $pb.BuilderInfo get info_ => _i;
  static SelectKlantbeeldMsg create() => SelectKlantbeeldMsg();
  SelectKlantbeeldMsg createEmptyInstance() => create();
  static $pb.PbList<SelectKlantbeeldMsg> createRepeated() => $pb.PbList<SelectKlantbeeldMsg>();
  static SelectKlantbeeldMsg getDefault() => _defaultInstance ??= create()..freeze();
  static SelectKlantbeeldMsg _defaultInstance;

  $core.int get klantId => $_get(0, 0);
  set klantId($core.int v) { $_setSignedInt32(0, v); }
  $core.bool hasKlantId() => $_has(0);
  void clearKlantId() => clearField(1);

  $core.String get achternaam => $_getS(1, '');
  set achternaam($core.String v) { $_setString(1, v); }
  $core.bool hasAchternaam() => $_has(1);
  void clearAchternaam() => clearField(2);
}

class PensioenAanspraakMsg extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('PensioenAanspraakMsg', package: const $pb.PackageName('jrb.grpc'))
    ..e<PensioenAanspraakTypeMsg>(1, 'type', $pb.PbFieldType.OE, PensioenAanspraakTypeMsg.TYPE_A, PensioenAanspraakTypeMsg.valueOf, PensioenAanspraakTypeMsg.values)
    ..aOS(2, 'uitkering')
    ..aInt64(3, 'einddatum')
    ..hasRequiredFields = false
  ;

  PensioenAanspraakMsg() : super();
  PensioenAanspraakMsg.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) : super.fromBuffer(i, r);
  PensioenAanspraakMsg.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) : super.fromJson(i, r);
  PensioenAanspraakMsg clone() => PensioenAanspraakMsg()..mergeFromMessage(this);
  PensioenAanspraakMsg copyWith(void Function(PensioenAanspraakMsg) updates) => super.copyWith((message) => updates(message as PensioenAanspraakMsg));
  $pb.BuilderInfo get info_ => _i;
  static PensioenAanspraakMsg create() => PensioenAanspraakMsg();
  PensioenAanspraakMsg createEmptyInstance() => create();
  static $pb.PbList<PensioenAanspraakMsg> createRepeated() => $pb.PbList<PensioenAanspraakMsg>();
  static PensioenAanspraakMsg getDefault() => _defaultInstance ??= create()..freeze();
  static PensioenAanspraakMsg _defaultInstance;

  PensioenAanspraakTypeMsg get type => $_getN(0);
  set type(PensioenAanspraakTypeMsg v) { setField(1, v); }
  $core.bool hasType() => $_has(0);
  void clearType() => clearField(1);

  $core.String get uitkering => $_getS(1, '');
  set uitkering($core.String v) { $_setString(1, v); }
  $core.bool hasUitkering() => $_has(1);
  void clearUitkering() => clearField(2);

  Int64 get einddatum => $_getI64(2);
  set einddatum(Int64 v) { $_setInt64(2, v); }
  $core.bool hasEinddatum() => $_has(2);
  void clearEinddatum() => clearField(3);
}

class AdresMsg extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo('AdresMsg', package: const $pb.PackageName('jrb.grpc'))
    ..aOS(1, 'adres')
    ..aOS(2, 'postcode')
    ..aOS(3, 'plaats')
    ..hasRequiredFields = false
  ;

  AdresMsg() : super();
  AdresMsg.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) : super.fromBuffer(i, r);
  AdresMsg.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) : super.fromJson(i, r);
  AdresMsg clone() => AdresMsg()..mergeFromMessage(this);
  AdresMsg copyWith(void Function(AdresMsg) updates) => super.copyWith((message) => updates(message as AdresMsg));
  $pb.BuilderInfo get info_ => _i;
  static AdresMsg create() => AdresMsg();
  AdresMsg createEmptyInstance() => create();
  static $pb.PbList<AdresMsg> createRepeated() => $pb.PbList<AdresMsg>();
  static AdresMsg getDefault() => _defaultInstance ??= create()..freeze();
  static AdresMsg _defaultInstance;

  $core.String get adres => $_getS(0, '');
  set adres($core.String v) { $_setString(0, v); }
  $core.bool hasAdres() => $_has(0);
  void clearAdres() => clearField(1);

  $core.String get postcode => $_getS(1, '');
  set postcode($core.String v) { $_setString(1, v); }
  $core.bool hasPostcode() => $_has(1);
  void clearPostcode() => clearField(2);

  $core.String get plaats => $_getS(2, '');
  set plaats($core.String v) { $_setString(2, v); }
  $core.bool hasPlaats() => $_has(2);
  void clearPlaats() => clearField(3);
}

