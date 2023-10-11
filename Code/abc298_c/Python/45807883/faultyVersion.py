N = int(input())
Q = int(input())
query = [list(map(int,input().split())) for _ in range(Q)]

box = [[] for _ in range(2*(10**5))]
i_in_box =[[] for _ in range(2*(10**5))]

for q in query:
    #print(q)
    if q[0] == 1:
        box[q[2]-1].append(q[1])
        i_in_box[q[1]-1].append(q[2])
    elif q[0] == 2:
        box[(q[1]-1)].sort()
        print(*box[(q[1]-1)])
    elif q[0] == 3:
        i_in_box[(q[1]-1)].sort()
        i_in_box[(q[1]-1)] = list(set(i_in_box[(q[1]-1)]))
        print(*i_in_box[(q[1]-1)])
