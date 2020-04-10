
Graphics 320, 240, 0, 2
Include "asciirender.bb"
AsciiGraphics()

Global scr_w = GetAsciiWidth()
Global scr_h = GetAsciiHeight() 

Print scr_w
Print scr_h

WaitKey()


; Graphics
c_player$ = "@"
c_direction_indicator$ = "*"

menu_offset = 30
Color 200, 200, 200
ClsColor 50, 50, 50

; Keys
Const K_LEAVE = 1
Const K_LEFT  = 203
Const K_RIGHT = 205
Const K_UP    = 200
Const K_DOWN  = 208


; game settings
map_w = menu_offset
map_h = scr_h

direction=0
Const D_LEFT  = 0
Const D_UP    = 1
Const D_RIGHT = 2
Const D_Down  = 3
Global px = map_w/2
Global py = map_h/2

; Other game stuff
timer = CreateTimer(60)
running = True


; Main loop
While running 

    ; Controls
    If KeyHit(K_LEAVE)
        running = False
    End If
    If KeyDown(K_LEFT)
        px = max(0, px-1)
        direction = D_LEFT
    End If
    If KeyDown(K_RIGHT)
        px = min(map_w-1, px+1)
        direction = D_RIGHT
    End If
    If KeyDown(K_UP)
        py = max(0, py-1)
        direction = D_UP
    End If
    If KeyDown(K_DOWN)
        py = min(map_h-1, py+1)
        direction = D_DOWN
    End If


    ; Graphics
    AsciiCls(".")

    ; Draw Player
    AsciiPlot(c_player, px, py)

    ; Draw Menu
    AsciiVLine("|", menu_offset, 0, scr_h-1)
    AsciiDrawRect(" ", menu_offset+1, 0, scr_w-1, scr_h-1, True)
    AsciiText("Res:", menu_offset+2, 1)
    AsciiText(scr_w+", "+scr_h, menu_offset+2, 2)
    AsciiText("Map:", menu_offset+2, 4)
    AsciiText(map_w+", "+map_h, menu_offset+2, 5)
    AsciiText("Pos:", menu_offset+2, 7)
    AsciiText(px+", "+py, menu_offset+2, 8)

    AsciiHLine("-", menu_offset+1,scr_w-1, 10)
    AsciiPlot("+", menu_offset, 10)


    x_ind = menu_offset+5
    y_ind = 14
    AsciiPlot(c_player, x_ind, y_ind)
    Select direction   
        Case D_UP
            y_ind = y_ind -1
        Case D_DOWN
            y_ind = y_ind+1
        Case D_LEFT
            x_ind = x_ind-1
        Case D_RIGHT
            x_ind = x_ind+1
        Default
            ; lol
    End Select
    AsciiPlot(c_direction_indicator, x_ind, y_ind)

    AsciiRender()

    WaitTimer(timer)
Wend

End

Function Min(a, b)
    If a < b
        Return a
    End If
    Return b
End Function

Function Max(a, b)
    Return -Min(-a, -b)
End Function



