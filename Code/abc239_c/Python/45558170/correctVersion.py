x1,y1,x2,y2 = map(int,input().split())

def culc(a,b,c,d):
    return (a-c)**2+(b-d)**2

for i in range(x1-2,x1+3):
    for j in range(y1-2,y1+3):
        if culc(i,j,x2,y2)==5 and culc(i,j,x1,y1)==5 :
            print("Yes")
            exit()
print("No")