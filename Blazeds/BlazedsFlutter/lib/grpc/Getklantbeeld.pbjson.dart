///
//  Generated code. Do not modify.
//  source: Getklantbeeld.proto
///
// ignore_for_file: camel_case_types,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name

const GeslachtMsg$json = const {
  '1': 'GeslachtMsg',
  '2': const [
    const {'1': 'MAN', '2': 0},
    const {'1': 'VROUW', '2': 1},
  ],
};

const PensioenAanspraakTypeMsg$json = const {
  '1': 'PensioenAanspraakTypeMsg',
  '2': const [
    const {'1': 'TYPE_A', '2': 0},
    const {'1': 'TYPE_B', '2': 1},
  ],
};

const KlantbeeldMsg$json = const {
  '1': 'KlantbeeldMsg',
  '2': const [
    const {'1': 'klantId', '3': 1, '4': 1, '5': 5, '10': 'klantId'},
    const {'1': 'voornaam', '3': 2, '4': 1, '5': 9, '10': 'voornaam'},
    const {'1': 'achternaam', '3': 3, '4': 1, '5': 9, '10': 'achternaam'},
    const {'1': 'geboorteDatum', '3': 4, '4': 1, '5': 3, '10': 'geboorteDatum'},
    const {'1': 'geslacht', '3': 5, '4': 1, '5': 14, '6': '.jrb.grpc.GeslachtMsg', '10': 'geslacht'},
    const {'1': 'saldo', '3': 6, '4': 1, '5': 9, '10': 'saldo'},
    const {'1': 'adres', '3': 7, '4': 1, '5': 11, '6': '.jrb.grpc.AdresMsg', '10': 'adres'},
    const {'1': 'aanspraken', '3': 8, '4': 3, '5': 11, '6': '.jrb.grpc.PensioenAanspraakMsg', '10': 'aanspraken'},
  ],
};

const SelectKlantbeeldMsg$json = const {
  '1': 'SelectKlantbeeldMsg',
  '2': const [
    const {'1': 'klantId', '3': 1, '4': 1, '5': 5, '10': 'klantId'},
    const {'1': 'achternaam', '3': 2, '4': 1, '5': 9, '10': 'achternaam'},
  ],
};

const PensioenAanspraakMsg$json = const {
  '1': 'PensioenAanspraakMsg',
  '2': const [
    const {'1': 'type', '3': 1, '4': 1, '5': 14, '6': '.jrb.grpc.PensioenAanspraakTypeMsg', '10': 'type'},
    const {'1': 'uitkering', '3': 2, '4': 1, '5': 9, '10': 'uitkering'},
    const {'1': 'einddatum', '3': 3, '4': 1, '5': 3, '10': 'einddatum'},
  ],
};

const AdresMsg$json = const {
  '1': 'AdresMsg',
  '2': const [
    const {'1': 'adres', '3': 1, '4': 1, '5': 9, '10': 'adres'},
    const {'1': 'postcode', '3': 2, '4': 1, '5': 9, '10': 'postcode'},
    const {'1': 'plaats', '3': 3, '4': 1, '5': 9, '10': 'plaats'},
  ],
};

