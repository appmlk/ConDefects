n,m = map(int,input().split())
s = input()
used_m = 0
used_r = 0
muji = m
rogo = 0
buy_t = 0
for i in s:
    if i == "1":
        if muji > 0:
            muji -= 1
            used_m += 1
        elif rogo > 0:
            rogo -= 1
            used_r += 1
        else:
            buy_t += 1
            used_r += 1
    elif i == "2":
        if rogo > 0:
            rogo -= 1
            used_r += 1
        else:
            buy_t += 1
            used_r += 1
    else:
        muji += used_m
        rogo += used_r

print(buy_t)