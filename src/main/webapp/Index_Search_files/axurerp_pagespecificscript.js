
var PageName = 'Index&Search';
var PageId = '61a05e3df8d94170a57b924f32c38c6b'
var PageUrl = 'Index_Search.html'
document.title = 'Index&Search';
var PageNotes = 
{
"pageName":"Index&amp;Search",
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

}

var u21 = document.getElementById('u21');
gv_vAlignTable['u21'] = 'center';
var u7 = document.getElementById('u7');
gv_vAlignTable['u7'] = 'center';
var u51 = document.getElementById('u51');

var u102 = document.getElementById('u102');
gv_vAlignTable['u102'] = 'top';
var u25 = document.getElementById('u25');

var u16 = document.getElementById('u16');
gv_vAlignTable['u16'] = 'center';
var u55 = document.getElementById('u55');

var u46 = document.getElementById('u46');
gv_vAlignTable['u46'] = 'top';
var u76 = document.getElementById('u76');

var u31 = document.getElementById('u31');
gv_vAlignTable['u31'] = 'center';
var u77 = document.getElementById('u77');

var u93 = document.getElementById('u93');
gv_vAlignTable['u93'] = 'top';
var u38 = document.getElementById('u38');

var u32 = document.getElementById('u32');

var u23 = document.getElementById('u23');

u23.style.cursor = 'pointer';
if (bIE) u23.attachEvent("onclick", u23Click);
else u23.addEventListener("click", u23Click, true);
InsertAfterBegin(document.body, "<DIV class='intcases' id='u23LinksClick'></DIV>")
var u23LinksClick = document.getElementById('u23LinksClick');
function u23Click(e) 
{
windowEvent = e;


	ToggleLinks(e, 'u23LinksClick');
}

InsertBeforeEnd(u23LinksClick, "<div class='intcaselink' onmouseout='SuppressBubble(event)' onclick='u23Clicku0a9a32bc219d458ea65a65c98f024bee(event)'>选中全部</div>");
function u23Clicku0a9a32bc219d458ea65a65c98f024bee(e)
{

	var obj1 = document.getElementById("u22");
    obj1.disabled = true;

	ToggleLinks(e, 'u23LinksClick');
}

InsertBeforeEnd(u23LinksClick, "<div class='intcaselink' onmouseout='SuppressBubble(event)' onclick='u23Clicku623304baddf84df0befc57369762b450(event)'>取消选中全部</div>");
function u23Clicku623304baddf84df0befc57369762b450(e)
{

	var obj1 = document.getElementById("u22");
    obj1.disabled = false;

	ToggleLinks(e, 'u23LinksClick');
}

var u62 = document.getElementById('u62');

var u53 = document.getElementById('u53');

var u87 = document.getElementById('u87');
gv_vAlignTable['u87'] = 'center';
var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u27 = document.getElementById('u27');

var u57 = document.getElementById('u57');
gv_vAlignTable['u57'] = 'top';
var u104 = document.getElementById('u104');

var u30 = document.getElementById('u30');

var u8 = document.getElementById('u8');

var u60 = document.getElementById('u60');

var u89 = document.getElementById('u89');
gv_vAlignTable['u89'] = 'center';
var u34 = document.getElementById('u34');

var u17 = document.getElementById('u17');

u17.style.cursor = 'pointer';
if (bIE) u17.attachEvent("onclick", Clicku17);
else u17.addEventListener("click", Clicku17, true);
function Clicku17(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u19','','fade',500);

}

}

var u64 = document.getElementById('u64');

u64.style.cursor = 'pointer';
if (bIE) u64.attachEvent("onclick", Clicku64);
else u64.addEventListener("click", Clicku64, true);
function Clicku64(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u40','hidden','fade',500);

}

}

var u100 = document.getElementById('u100');

u100.style.cursor = 'pointer';
if (bIE) u100.attachEvent("onclick", Clicku100);
else u100.addEventListener("click", Clicku100, true);
function Clicku100(e)
{
windowEvent = e;


if (true) {

	self.location.href="resources/reload.html#" + encodeURI(PageUrl + GetQuerystring());

}

}

var u19 = document.getElementById('u19');

var u58 = document.getElementById('u58');
gv_vAlignTable['u58'] = 'top';
var u49 = document.getElementById('u49');
gv_vAlignTable['u49'] = 'center';
var u79 = document.getElementById('u79');

if (bIE) u79.attachEvent("onmouseover", MouseOveru79);
else u79.addEventListener("mouseover", MouseOveru79, true);
function MouseOveru79(e)
{
windowEvent = e;

if (!IsTrueMouseOver('u79',e)) return;
if (true) {

	SetPanelState('u83', 'pd0u83','swing','left',500,'swing','left',500);

}

}

var u81 = document.getElementById('u81');

if (bIE) u81.attachEvent("onmouseover", MouseOveru81);
else u81.addEventListener("mouseover", MouseOveru81, true);
function MouseOveru81(e)
{
windowEvent = e;

if (!IsTrueMouseOver('u81',e)) return;
if (true) {

	SetPanelState('u83', 'pd0u83','swing','left',500,'swing','left',500);

}

}

var u97 = document.getElementById('u97');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u97ann'), "<div id='u97Note' class='annnoteimage' style='left:" + x + ";top:" + y + "'></div>");
$('#u97Note').click(function(e) { ToggleWorkflow(e, 'u97', 300, 150, false); return false; });
var u97Ann = 
{
"label":"?",
"Description":"系统管理,管理员登录后可见"};

u97.style.cursor = 'pointer';
if (bIE) u97.attachEvent("onclick", Clicku97);
else u97.addEventListener("click", Clicku97, true);
function Clicku97(e)
{
windowEvent = e;


if (true) {

	self.location.href="Admin.html" + GetQuerystring();

}

}

var u85 = document.getElementById('u85');
gv_vAlignTable['u85'] = 'center';
var u11 = document.getElementById('u11');

var u41 = document.getElementById('u41');

var u71 = document.getElementById('u71');
gv_vAlignTable['u71'] = 'top';
var u15 = document.getElementById('u15');

u15.style.cursor = 'pointer';
if (bIE) u15.attachEvent("onclick", Clicku15);
else u15.addEventListener("click", Clicku15, true);
function Clicku15(e)
{
windowEvent = e;


if (true) {

	self.location.href="Search_Results.html" + GetQuerystring();

}

}

var u45 = document.getElementById('u45');
gv_vAlignTable['u45'] = 'top';
var u36 = document.getElementById('u36');
gv_vAlignTable['u36'] = 'top';
var u75 = document.getElementById('u75');

u75.style.cursor = 'pointer';
if (bIE) u75.attachEvent("onclick", Clicku75);
else u75.addEventListener("click", Clicku75, true);
function Clicku75(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u76','','none',500);

}

}
gv_vAlignTable['u75'] = 'top';
var u66 = document.getElementById('u66');
gv_vAlignTable['u66'] = 'center';
var u37 = document.getElementById('u37');
gv_vAlignTable['u37'] = 'top';
var u2 = document.getElementById('u2');

var u92 = document.getElementById('u92');
gv_vAlignTable['u92'] = 'top';
var u83 = document.getElementById('u83');

var u95 = document.getElementById('u95');
gv_vAlignTable['u95'] = 'center';
var u22 = document.getElementById('u22');

var u13 = document.getElementById('u13');

var u52 = document.getElementById('u52');
gv_vAlignTable['u52'] = 'center';
var u43 = document.getElementById('u43');

var u0 = document.getElementById('u0');

var u3 = document.getElementById('u3');
gv_vAlignTable['u3'] = 'center';
var u47 = document.getElementById('u47');

u47.style.cursor = 'pointer';
if (bIE) u47.attachEvent("onclick", Clicku47);
else u47.addEventListener("click", Clicku47, true);
function Clicku47(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u40','hidden','fade',500);

	SetPanelState('u67', 'pd0u67','fade','',500,'fade','',500);

}

}

var u68 = document.getElementById('u68');
gv_vAlignTable['u68'] = 'top';
var u86 = document.getElementById('u86');

if (bIE) u86.attachEvent("onmouseout", MouseOutu86);
else u86.addEventListener("mouseout", MouseOutu86, true);
function MouseOutu86(e)
{
windowEvent = e;

if (!IsTrueMouseOut('u86',e)) return;
if (true) {

	SetPanelState('u83', 'pd1u83','swing','right',500,'swing','right',500);
function waitu9d2f182afec64f81887674d29dec16711() {

	SetPanelVisibility('u83','hidden','none',500);
}
setTimeout(waitu9d2f182afec64f81887674d29dec16711, 600);

}

}

var u90 = document.getElementById('u90');

u90.style.cursor = 'pointer';
if (bIE) u90.attachEvent("onclick", Clicku90);
else u90.addEventListener("click", Clicku90, true);
function Clicku90(e)
{
windowEvent = e;


if (true) {

	self.location.href="Blast.html" + GetQuerystring();

}

}

var u73 = document.getElementById('u73');
gv_vAlignTable['u73'] = 'center';
var u84 = document.getElementById('u84');

var u20 = document.getElementById('u20');

var u50 = document.getElementById('u50');

u50.style.cursor = 'pointer';
if (bIE) u50.attachEvent("onclick", Clicku50);
else u50.addEventListener("click", Clicku50, true);
function Clicku50(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u40','hidden','fade',500);

}

}

var u28 = document.getElementById('u28');
gv_vAlignTable['u28'] = 'center';
var u24 = document.getElementById('u24');
gv_vAlignTable['u24'] = 'top';
var u54 = document.getElementById('u54');
gv_vAlignTable['u54'] = 'center';
var u99 = document.getElementById('u99');
gv_vAlignTable['u99'] = 'top';
var u39 = document.getElementById('u39');
gv_vAlignTable['u39'] = 'top';
var u69 = document.getElementById('u69');

u69.style.cursor = 'pointer';
if (bIE) u69.attachEvent("onclick", Clicku69);
else u69.addEventListener("click", Clicku69, true);
function Clicku69(e)
{
windowEvent = e;


if (true) {

	SetPanelState('u40', 'pd1u40','fade','',500,'fade','',500);

}

}

var u78 = document.getElementById('u78');

var u4 = document.getElementById('u4');

var u103 = document.getElementById('u103');

var u6 = document.getElementById('u6');

u6.style.cursor = 'pointer';
if (bIE) u6.attachEvent("onclick", Clicku6);
else u6.addEventListener("click", Clicku6, true);
function Clicku6(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u76','hidden','none',500);

}

}

var u96 = document.getElementById('u96');
gv_vAlignTable['u96'] = 'top';
var u61 = document.getElementById('u61');

u61.style.cursor = 'pointer';
if (bIE) u61.attachEvent("onclick", Clicku61);
else u61.addEventListener("click", Clicku61, true);
function Clicku61(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u40','hidden','fade',500);

	SetPanelState('u67', 'pd1u67','fade','',500,'fade','',500);

}

}

var u35 = document.getElementById('u35');
gv_vAlignTable['u35'] = 'center';
var u26 = document.getElementById('u26');

u26.style.cursor = 'pointer';
if (bIE) u26.attachEvent("onclick", Clicku26);
else u26.addEventListener("click", Clicku26, true);
function Clicku26(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u19','hidden','fade',500);

}

}

var u65 = document.getElementById('u65');

var u56 = document.getElementById('u56');
gv_vAlignTable['u56'] = 'center';
var u82 = document.getElementById('u82');
gv_vAlignTable['u82'] = 'center';
var u94 = document.getElementById('u94');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u94ann'), "<div id='u94Note' class='annnoteimage' style='left:" + x + ";top:" + y + "'></div>");
$('#u94Note').click(function(e) { ToggleWorkflow(e, 'u94', 300, 150, false); return false; });
var u94Ann = 
{
"label":"?",
"Description":"添加新条目,仅在登录后显示"};

u94.style.cursor = 'pointer';
if (bIE) u94.attachEvent("onclick", Clicku94);
else u94.addEventListener("click", Clicku94, true);
function Clicku94(e)
{
windowEvent = e;


if (true) {

	self.location.href="Inserting-STEP1.html" + GetQuerystring();

}

}

var u5 = document.getElementById('u5');
gv_vAlignTable['u5'] = 'center';
var u12 = document.getElementById('u12');

var u9 = document.getElementById('u9');
gv_vAlignTable['u9'] = 'center';
var u42 = document.getElementById('u42');
gv_vAlignTable['u42'] = 'center';
var u33 = document.getElementById('u33');
gv_vAlignTable['u33'] = 'center';
var u72 = document.getElementById('u72');

u72.style.cursor = 'pointer';
if (bIE) u72.attachEvent("onclick", Clicku72);
else u72.addEventListener("click", Clicku72, true);
function Clicku72(e)
{
windowEvent = e;


if (true) {

	SetPanelState('u40', 'pd0u40','fade','',500,'fade','',500);

}

}

var u63 = document.getElementById('u63');
gv_vAlignTable['u63'] = 'center';
var u18 = document.getElementById('u18');
gv_vAlignTable['u18'] = 'center';
var u48 = document.getElementById('u48');

var u67 = document.getElementById('u67');

var u88 = document.getElementById('u88');

u88.style.cursor = 'pointer';
if (bIE) u88.attachEvent("onclick", Clicku88);
else u88.addEventListener("click", Clicku88, true);
function Clicku88(e)
{
windowEvent = e;


if (true) {

	self.location.href="AdvSearch.html" + GetQuerystring();

}

}

var u80 = document.getElementById('u80');
gv_vAlignTable['u80'] = 'center';
var u101 = document.getElementById('u101');
gv_vAlignTable['u101'] = 'center';
var u10 = document.getElementById('u10');
gv_vAlignTable['u10'] = 'top';
var u40 = document.getElementById('u40');

var u70 = document.getElementById('u70');
gv_vAlignTable['u70'] = 'center';
var u14 = document.getElementById('u14');
gv_vAlignTable['u14'] = 'center';
var u44 = document.getElementById('u44');
gv_vAlignTable['u44'] = 'center';
var u74 = document.getElementById('u74');
gv_vAlignTable['u74'] = 'top';
var u29 = document.getElementById('u29');

u29.style.cursor = 'pointer';
if (bIE) u29.attachEvent("onclick", Clicku29);
else u29.addEventListener("click", Clicku29, true);
function Clicku29(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u19','hidden','fade',500);

}

}

var u59 = document.getElementById('u59');

var u98 = document.getElementById('u98');
gv_vAlignTable['u98'] = 'center';
var u91 = document.getElementById('u91');
gv_vAlignTable['u91'] = 'center';
if (window.OnLoad) OnLoad();
