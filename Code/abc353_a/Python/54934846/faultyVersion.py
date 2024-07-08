x=int(input())
cont=0
cad=[int(x) for x in input().split()]
for i in range(1,len(cad)):
    if cad[0]<cad[i]:
        print(i+1)
        break
    elif cad[0]>cad[i]:
        cont+=1
if len(cad)-1==cont:
    print(-1)