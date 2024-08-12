<h1>Triones Data</h1>

This repo contains the data required to control Triones LED  lights. I am not sure if this is all
the features that these lights support. The slip with the lights I got instructs you to use the
HappyLighting app, there are more functions in the app than the lights I have seem to support though.
For example there is a timing tab but my lights could not be selected on this tab.

The service and characteristic UUIDs can be found in the [TrionesDataProvider](TrionesDataProvider.kt) class.
The write type is `WRITE_TYPE_NO_RESPONSE`