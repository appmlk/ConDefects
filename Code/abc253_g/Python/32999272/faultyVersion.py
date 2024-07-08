N, L, R = map(int, input().split())

#まずfor文
sum = 0
sum2 = 0
Ltl = 0#Lのタプルの左の値
Ltr = 0#Lのタプルの右の値
Rtl = 0#Rのタプルの左の値
Rtr = 0#Rのタプルの右の値
for i in range(1,N):
    sum += N-i
    if(L<=sum):
        Ltl = i
        sum2 = sum - (N-i)
        #これでLtrの値がわかある
        indLtr = L - sum2
        Ltr = indLtr + Ltl
        #print(Ltl, Ltr)
        break

#Lのタプルより小さくなることはないのでLtlの値から探索する.
for j in range(Ltl,N):
    sum2 += N-j
    if(R<=sum2):
        Rtl = j
        sum3 = sum2 - (N-j)
        indRtr = R - sum3
        Rtr = indRtr + Rtl
        #print(Rtl, Rtr)
        break
notsee = []
see = []
for k in range(1,Ltl):
    notsee.append(k)
for l in range(0,Rtl-Ltl):
    notsee.append(N-l)
for m in range(Ltl,N - (Rtl - Ltl) +1):
    see.append(m)
#print(notsee)
#print(see)
if(len(see)>=Ltr-Ltl):
    see.insert(Ltr-Ltl-1,see.pop(0))
else:
    #exchange = see.pop(0)
    a = Ltr - Ltl
    prelen = len(see)
    for i in range(1, a+1-prelen):
        see.append(notsee.pop(-1))
        #print(see)
    notsee.append(see.pop(0))
    print(see)
   #長さが変わる前に保存
    for j in range(1, a-prelen):
        notsee.append(see.pop(-1))
        #print(see)
for k in range(1,Rtr-Rtl +1):
    temp = see[0]
    see[0] = see[k]
    see[k] = temp
print(*(notsee+see))