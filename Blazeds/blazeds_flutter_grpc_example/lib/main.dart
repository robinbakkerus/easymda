import 'package:flutter/material.dart';
import 'data/app_data.dart';
import 'events/app_events.dart';
// import 'grpc/gprc_client.dart';
import 'service/klantbeeld_srv.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  AppData _appData;
  String _naam = "..";
  

  _MyHomePageState() {
    _appData = AppData();
    AppEvents.onKlantbeeldSelected(_onKlantbeeld);
  }

  void _onKlantbeeld(KlantbeeldSelectedEvent event) {
    setState(() {
      this._naam = _appData.klantbeeld.achternaam;
    });
  }

  void _getKlantbeeld() {
    print("TODO 0");
    //GprcClient().getKlantbeeld([]);
    IKlantbeeldService().getKlantbeeld(["test"]);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_naam',
              style: Theme.of(context).textTheme.display1,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _getKlantbeeld,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
