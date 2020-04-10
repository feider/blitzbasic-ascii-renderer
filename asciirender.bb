

Global ascii_font_size = 0
Global ascii_width = 1
Global ascii_height = 1
Global ascii_font_width = 0
Global ascii_font_height = 0
Dim ascii_screen$(1, 1)

Function GetAsciiWidth()
    Return ascii_width
End Function
Function GetAsciiHeight()
    Return ascii_height
End Function

Function AsciiGraphics()
    ascii_font_width = FontWidth()
    ascii_font_height = FontHeight()
    ascii_width = Int(GraphicsWidth()/ascii_font_width)
    ascii_height = Int(GraphicsHeight()/ascii_font_height)
    Dim ascii_screen$(ascii_width, ascii_height)
    AsciiCls(" ")
End Function

Function AsciiRender()
    Cls
    For x = 0 To ascii_width-1
        For y = 0 To ascii_height -1
            x_scr = x*ascii_font_width
            y_scr = y*ascii_font_height
            Text x_scr, y_scr, ascii_screen(x, y)
        Next
    Next
End Function

Function AsciiCls(char$)
    AsciiDrawRect(char, 0, 0, ascii_width-1, ascii_height-1, True)
End Function

Function AsciiHLine(char$, x_start, x_end, y)
    If x_end < x_start
        tmp = x_end
        x_end = x_start
        x_start = tmp
    End If
    For x = x_start To x_end
        AsciiPlot(char, x, y)
    Next
End Function

Function AsciiVLine(char$, x, y_start, y_end)
    If y_end < y_start
        tmp = y_end
        y_end = y_start
        y_start = tmp
    End If
    For y = y_start To y_end
        AsciiPlot(char, x, y)
    Next
End Function

Function AsciiDrawRect(char$, x_start, y_start, x_end, y_end, filled)
    If x_end < x_start
        tmp = x_end
        x_end = x_start
        x_start = tmp
    End If
    If y_end < y_start
        tmp = y_end
        y_end = y_start
        y_start = tmp
    End If
    If Filled
        For x = x_start To x_end
            For y = y_start To y_end
            AsciiPlot(char, x, y)
            Next
        Next
    Else
        AsciiHLine(char$, x_start, x_end, y_start)
        AsciiHLine(char$, x_start, x_end, y_end)
        AsciiVLine(char$, x_start, y_start, y_end)
        AsciiVLine(char$, x_end, y_start, y_end)
    End If
End Function

Function AsciiText(text$, x, y)
    For i = 1 To Len(text)
        char$ = Mid(text, i, 1)
        AsciiPlot(char, x+i-1, y)
    Next
End Function

Function AsciiPlot(char$, x, y)
    If AsciiCheckInScreen(x, y)
        ascii_screen$(x, y)=char
    End If
End Function

Function AsciiCheckInScreen(x, y)
    If x >= 0 And x<ascii_width And y >=0 And y<ascii_height
        Return True
    End If
    Return False
End Function

Function AsciiMinI(a, b)
    If a < b
        Return a
    End If
    Return b
End Function

Function AsciiMaxI(a, b)
    Return -AsciiMinI(-a, -b)
End Function


