N = int(input())
key = N
point = 0
l = []
while  key > 0:
    s = key % 10
    key = key // 10
    point += 1
    l.append(s)

if point <= 3:
    print(N)
elif point == 4:
    l[0] = 0
    for i in range(point-1, -1, -1):
        print(l[i],end='')
elif point == 5:
    l[0] = 0
    l[1] = 1
    for i in range(point-1, -1, -1):
        print(l[i],end='')
elif point == 6:
    l[0] = 0
    l[1] = 0
    l[2] = 0
    for i in range(point-1, -1, -1):
        print(l[i],end='')
elif point == 7:
    l[0] = 0
    l[1] = 0
    l[2] = 0
    l[3] = 0
    for i in range(point-1, -1, -1):
        print(l[i],end='')
elif point == 8:
    l[0] = 0
    l[1] = 0
    l[2] = 0
    l[3] = 0
    l[4] = 0
    for i in range(point-1, -1, -1):
        print(l[i],end='')
elif point == 9:
    l[0] = 0
    l[1] = 0
    l[2] = 0
    l[3] = 0
    l[4] = 0
    l[5] = 0
    for i in range(point-1, -1, -1):
        print(l[i],end='')

   

   
    
