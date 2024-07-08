
def rint(): return list(map(int, input().split())) 

N, M = rint()
S = input()
shirts = M
buy = 0
logo = 0
for i in range(N):
    if S[i] == '1':
        if shirts == 0:
            buy += 1
        else:
            if shirts == logo:
                logo -= 1
            shirts -= 1
    elif S[i] == '2':
        if logo == 0:
            buy += 1
        else:
            shirts -= 1
            logo -= 1
    else:
        logo = buy
        shirts = buy+M

print(buy)