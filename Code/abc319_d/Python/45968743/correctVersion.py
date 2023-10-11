N,M=map(int,input().split())
L=list(map(int,input().split()))

def jadge(col_num):
    row_num=1
    count=-1
    for i in range(N):
        count+=L[i]+1
        if count>col_num:
            row_num+=1
            count=L[i]
        #print(L[i],count,row_num)
    return row_num

def bs():
    left=max(L)-1
    right=sum(L)+N+1
    center=(left+right)//2
    #print(left,right,center)
    while right-left>1:
        #print(M,center,jadge(center),M<=jadge(center))
        if M>=jadge(center):
            right=min(center,right-1)
            center=(left+right)//2
        else:
            left=max(center,left+1)
            center=(left+right)//2
    return right
    
print(bs())


"""test
N,M=map(int,input().split())
L=list(map(int,input().split()))

cumsum_L=[L[0]]
for i in range(1,N):
    cumsum_L.append(cumsum_L[i-1]+L[i]+1)
print(cumsum_L)
#print(len(cumsum_L),N,cumsum_L[-1])

def jadge(col_num):
    left_i=-1
    right_i=N-1
    #one_col_num=col_num
    
    minus=0
    for i in range(M):
        left_i=min(N-1,left_i+1)
        right_i=N-1
        center_i=(left_i+right_i)//2
        if cumsum_L[left_i]-minus>col_num:
            #その行の累積和が許容できる列数より大きいとき
            #print(cumsum_L[left_i],minus,col_num)
            return False
        #print("col_num",col_num)

        while right_i-left_i>1:
            if cumsum_L[left_i]-minus==col_num:
                #print(0,right_i,cumsum_L[right_i]-minus)
                #print(0)
                #left_i=right_i
                break
            elif cumsum_L[center_i]-minus<=col_num:
                #print(1,left_i,center_i,right_i,cumsum_L[center_i]-minus)
                left_i=max(center_i,left_i+1)
                left_i=center_i
                center_i=(left_i+right_i)//2
            else:
                #print("col_num",col_num)
                #print(2,left_i,center_i,right_i,cumsum_L[center_i]-minus)
                #right_i=center_i
                right_i=min(center_i,right_i-1)
                center_i=(left_i+right_i)//2

        #col_num+=one_col_num
        #print(left_i,cumsum_L[left_i])
        #print(i,"--",left_i,center_i,right_i)
        if left_i>N-1:
            return False
        minus=cumsum_L[left_i]
    #print("------")
    if cumsum_L[center_i]-minus<=col_num:
        return True
    else:
        return False

print(jadge(188))
left_col=1
right_col=cumsum_L[-1]
center_col=(left_col+right_col)//2
ans=-1
#print(right_col)
#print(jadge(26))
"""
"""
while right_col-left_col>0:
    if jadge(left_col):
        left_col=right_col
        break
    elif jadge(center_col)==True:
        right_col=min(right_col-1,center_col)
        center_col=(left_col+right_col)//2
    else:
        left_col=max(left_col+1,center_col)
        center_col=(left_col+right_col)//2
    #print(left_col,center_col,right_col,jadge(center_col))

print(right_col)
"""
"""
N, M = map(int, input().split())
L = list(map(int, input().split()))

max_L = max(L)#文字の横幅の最大値


def judge(m):
    #もし、列の幅が文字の単語の長さより小さければFalse
    if max_L > m:
        return False
    now_width = 1 << 63#巨大数
    cnt = 0
    #単語の数だけ実施
    for l in L:
        if now_width + l + 1 > m:
            cnt += 1
            now_width = l
        else:
            now_width += l + 1
    return cnt <= M


ok = 1 << 63
ng = 0
while ok - ng > 1:
    mid = (ok + ng) // 2
    if judge(mid):
        ok = mid
    else:
        ng = mid
print(ok)
"""


