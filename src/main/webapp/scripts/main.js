if (location.href.toString().indexOf('file://localhost/') == 0) {
	location.href = location.href.toString().replace('file://localhost/', 'file:///');
}

(function () {
    setUpController();

    var _settings = {};
    _settings.projectId = configuration.prototypeId;
    _settings.isAxshare = configuration.isAxshare;
    _settings.loadFeedbackPlugin = configuration.loadFeedbackPlugin;

    $axure.player.settings = _settings;

    $(window).bind('load', function () {
        if (CHROME_5_LOCAL && !$('body').attr('pluginDetected')) {
            window.location = '/resources/chrome/chrome.html';
        }
    });

    $(document).ready(function () {
        $axure.page.bind('load.start', mainFrame_onload);
        $axure.messageCenter.addMessageListener(messageCenter_message);

        if($axure.player.settings.loadFeedbackPlugin == true) {
            if($axure.player.settings.isAxshare == true) {
                $axure.utils.loadJS('/Scripts/plugins/feedback/feedback.js');
            } else {
                $axure.utils.loadJS('http://share.axure.com/Scripts//plugins/feedback/feedback.js');
            }
        }

        initialize();
        $('#outerContainer').splitter({
            //outline: true,
            sizeLeft: 250
        });
        $('#leftPanel').width(250);

        $(window).resize(function () {
            resizeContent();
        });

        $('#maximizePanelContainer').hide();

        initializeLogo();

        $(window).resize();
    });


})();

lastLeftPanelWidth = 250;

function messageCenter_message(message, data) {
    if (message == 'expandFrame') expand();
}

function resizeContent() {
    var newHeight = $(window).height();
    var controlContainerHeight = newHeight - 37;
    if ($('#interfaceControlFrameLogoContainer').is(':visible')) {
        controlContainerHeight -= $('#interfaceControlFrameLogoContainer').height() + 16;
    }

    $('#outerContainer').height(newHeight);
    $('#leftPanel').height(newHeight);
    $('#interfaceControlFrame').height(newHeight);
    $('#interfaceControlFrameContainer').height(controlContainerHeight);

    $('#rightPanel').height(newHeight);
    $('#mainFrame').height(newHeight);

    $('#rightPanel').width($(window).width() - $('#leftPanel').width() - $('.vsplitbar').width());

    $('#outerContainer').trigger('resize');
}

function collapse() {
    $('#maximizePanelContainer').show();
    lastLeftPanelWidth = $('#leftPanel').width();
    $('#leftPanel').hide();
    $('.vsplitbar').hide();
    $('#rightPanel').width($(window).width());
    $(window).resize();
}

function expand() {
    $('#maximizePanelContainer').hide();
    $('#leftPanel').width(lastLeftPanelWidth);
    $('#leftPanel').show();
    $('.vsplitbar').show();
    $('#rightPanel').width($(window).width() - $('#leftPanel').width() - $('.vsplitbar').width());
    $(window).resize();
}

function initialize() {
    var mainFrame = document.getElementById("mainFrame");
    var pageName = QueryString("Page");
    if (pageName.length > 0) {
        mainFrame.contentWindow.location.href = pageName + ".html";
    }
    else {
        mainFrame.contentWindow.location.href = 
            (sitemap.rootNodes.length > 0 ? sitemap.rootNodes[0].url : "about:blank");
    }
}

function initializeLogo() {
    if (configuration.logoImagePath) {
        $('#interfaceControlFrameLogoImageContainer').html('<img id="logoImage" src="" />');
        $('#logoImage').attr('src', configuration.logoImagePath).load(function () {
            resizeContent();
        });
    } else {
        $('#interfaceControlFrameLogoImageContainer').hide();
    }

    if (configuration.logoImageCaption) {
        $('#interfaceControlFrameLogoCaptionContainer').html(configuration.logoImageCaption);
    } else {
        $('#interfaceControlFrameLogoCaptionContainer').hide();
    }

    if (!$('#interfaceControlFrameLogoImageContainer').is(':visible') && !$('#interfaceControlFrameLogoCaptionContainer').is(':visible')) {
        $('#interfaceControlFrameLogoContainer').hide();
    }
}

function mainFrame_onload() {
    //var mainFrame = document.getElementById("mainFrame");
    if ($axure.page.pageName) {
        //document.title = mainFrame.contentWindow.PageName;
        document.title = $axure.page.pageName;
    }
}

function QueryString(query) {
    var qstring = self.location.href.split("?");
    if (qstring.length < 2) return ""

    var prms = qstring[1].split("&");
    var frmelements = new Array();
    var currprmeter, querystr = "";

    for (i = 0; i < prms.length; i++) {
        currprmeter = prms[i].split("=");
        frmelements[i] = new Array();
        frmelements[i][0] = currprmeter[0];
        frmelements[i][1] = currprmeter[1];
    }

    for (j = 0; j < frmelements.length; j++) {
        if (frmelements[j][0] == query) {
            querystr = frmelements[j][1];
            break;
        }
    }
    return querystr;
}