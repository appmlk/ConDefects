# B
n = int(input())
a = list(map(int, input().split()))
#n = 4
#a = [2, 5, 1, 2]
b = []
i_bef = 0
#print(a)
for i in a:
    #print(i)
    if i_bef == 0: 
        b.append(i)
        i_bef = i
        continue
    dif = i - i_bef
    #print("dif:", dif)
    if abs(dif) > 1:
        if dif > 0:
            for j in range(1,abs(dif)):
                #print("j", j)
                b.append(i_bef + j)
            b.append(i)
        else:
            for j in range(1,abs(dif)):
                #print("j", j)
                b.append(i_bef - j)
            b.append(i)
    else:
        b.append(i)
    i_bef = i
    #print("i_bef", i_bef)
print(*b)