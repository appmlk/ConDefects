S = list(input())
T = list(input())

S_count = []
T_count  = []
sf = S[0]
S_count.append([sf, 1])
for i in range(1, len(S)):
    if sf == S[i]:
        S_count[-1][1] += 1
    else:
        sf = S[i]
        S_count.append([sf, 1])

tf = T[0]
T_count.append([tf, 1])
for i in range(1,  len(T)):
    if tf == T[i]:
        T_count[-1][1] += 1
    else:
        tf = T[i]
        T_count.append([tf, 1])

tf = True
if len(S_count) != len(T_count):
    tf = False
else:
    for i in range(len(S_count)):
        if S_count[i][1] > T_count[i][1]:
            tf = False
        if S_count[i][1] == 1 and T_count[i][1] >= 2:
            tf = False
        if S_count[i][0] != T_count[i][0]:
            tf = False

if tf:
    print("Yes")
else:
    print("No")