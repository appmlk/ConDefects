import itertools
import math

m = []
for i in range(3):
    x, y,z  = map(int, input().split())
    m.append(x)
    m.append(y)
    m.append(z)

print(m)

cklist = [[[2,3],[4,7],[5,9]] \
         ,[[1,3],[5,8]]  \
         ,[[1,2],[6,9],[5,7]]  \
         ,[[1,7],[5,6]]  \
         ,[[2,8],[4,6],[1,9],[3,7]]   \
         ,[[3,9],[4,5]]  \
         ,[[1,4],[3,5],[8,9]]  \
         ,[[2,5],[7,9]]  \
         ,[[1,5],[3,6],[7,8]]]

lp = [0,1,2,3,4,5,6,7,8]

def gakkari(c,v,w):
    for i in cklist[c]:
        s = 10* w[i[0]-1] + w[i[1]-1]
        #print(s,c,v,i,w)
        if s == v or s == 10*v :
            return True
    return False
    
kei =math.factorial(9)
cnt = 0
for s in itertools.permutations(lp, 9 ):
    l = list(s)
    w = [0 for _ in range(9)]
    for i in l:
        w[i] = m[i]
        ## gakkari chk
        if gakkari(i,m[i],w) :
            break
        
    else:
        cnt += 1


#print(w)
#print(cklist)


print(cnt/kei )

