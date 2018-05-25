AutoItSetOption("WinTitleMatchMode","2")
WinWait("Authentication Required")
$title = WinGetTitle("Authentication Required") ; retrives whole window title
$UN=WinGetText($title,"User Name:")
ControlSend($title,"",$UN,"testlab1");Sets Username
$PWD=WinGetText($title,"Password:")
Send("{TAB 1}")
ControlSend($title,"",$PWD,"04Music");Sets PWD
Send("{ENTER}")