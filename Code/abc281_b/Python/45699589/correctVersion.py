s = input()
n = len(s)
keyA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
key0 = "0123456789"
turn = 0
idx = 0
i = 0
while i < n:
    if turn == 0:
        if s[i] in keyA:
            turn = 1
        else:
            break
    elif turn == 1:
        if s[i] == "0":
            break
        else:
            turn = 2
            idx = 1
    elif turn == 2:
        if s[i] in key0 and idx < 6:
            idx += 1
        else:
            break
        if idx == 6:
            idx = 0
            turn = 0
            i += 1
            if i >= n or s[i] not in keyA:
                break
    i += 1
else:
    print("Yes" if idx == 0 and turn == 0 else "No")
    exit()
print("No")














