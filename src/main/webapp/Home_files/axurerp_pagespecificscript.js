
var PageName = 'Home';
var PageId = 'f83c4425bebe4338890580e3b648cce0'
var PageUrl = 'Home.html'
document.title = 'Home';
var PageNotes = 
{
"pageName":"Home",
"showNotesNames":"False"}
var $OnLoadVariable = '';

var $CSUM;

var hasQuery = false;
var query = window.location.hash.substring(1);
if (query.length > 0) hasQuery = true;
var vars = query.split("&");
for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0].length > 0) eval("$" + pair[0] + " = decodeURIComponent(pair[1]);");
} 

if (hasQuery && $CSUM != 1) {
alert('Prototype Warning: The variable values were too long to pass to this page.\nIf you are using IE, using Firefox will support more data.');
}

function GetQuerystring() {
    return '#OnLoadVariable=' + encodeURIComponent($OnLoadVariable) + '&CSUM=1';
}

function PopulateVariables(value) {
    var d = new Date();
  value = value.replace(/\[\[OnLoadVariable\]\]/g, $OnLoadVariable);
  value = value.replace(/\[\[PageName\]\]/g, PageName);
  value = value.replace(/\[\[GenDay\]\]/g, '15');
  value = value.replace(/\[\[GenMonth\]\]/g, '2');
  value = value.replace(/\[\[GenMonthName\]\]/g, 'February');
  value = value.replace(/\[\[GenDayOfWeek\]\]/g, 'Wednesday');
  value = value.replace(/\[\[GenYear\]\]/g, '2012');
  value = value.replace(/\[\[Day\]\]/g, d.getDate());
  value = value.replace(/\[\[Month\]\]/g, d.getMonth() + 1);
  value = value.replace(/\[\[MonthName\]\]/g, GetMonthString(d.getMonth()));
  value = value.replace(/\[\[DayOfWeek\]\]/g, GetDayString(d.getDay()));
  value = value.replace(/\[\[Year\]\]/g, d.getFullYear());
  return value;
}

function OnLoad(e) {

if (true) {
function waitu4ab70580078e46bd90f3741fa566b6591() {

	self.location.href="Index_Search.html" + GetQuerystring();
}
setTimeout(waitu4ab70580078e46bd90f3741fa566b6591, 2000);

}

}

var u0 = document.getElementById('u0');

var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u2 = document.getElementById('u2');
gv_vAlignTable['u2'] = 'top';
if (window.OnLoad) OnLoad();
