N=int(input())

cnt=0
#AABCDDEFE
for i in range(100000,1000000):
    cnt+=1
    if cnt==N:
        print(int(str(i)[0]+str(i)[0]+str(i)[1]+str(i)[2]+str(i)[3]+str(i)[3]+str(i)[4]+str(i)[5]+str(i)[4]))
        exit()