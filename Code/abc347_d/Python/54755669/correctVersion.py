a,b,c = map(int, input().split())

cbit = []
popcount = 0
for i in range(60):
    if c & 1<<i != 0:
        cbit.append(1)
        popcount += 1
    else:
        cbit.append(0)
# print(cbit,popcount)

if a+b >= popcount and (a+b)%2 == popcount%2:
    d = (a+b-popcount)//2
    if a-d < 0 or b-d < 0:
        print(-1)
    elif a+b+popcount > 120:
        print(-1)
    else:
        # print(a,b,d)
        ansa = 0
        ansb = 0
        acount = 0
        dcount = 0
        flag = True
        if a-d == 0:
            flag = False
        flag2 = True
        if d == 0:
            flag2 = False
        for i,ibit in enumerate(cbit):
            if ibit == 1:
                if flag:
                    ansa += 1<<i
                    acount += 1
                    if acount == a-d:
                        flag = False
                else:
                    ansb += 1<<i
            else:
                if flag2:
                    ansa += 1<<i
                    ansb += 1<<i
                    dcount += 1
                    if dcount == d:
                        flag2 = False

        print(ansa,ansb)
        # print(ansa.bit_count(),ansb.bit_count(),ansa^ansb)
else:
    print(-1)