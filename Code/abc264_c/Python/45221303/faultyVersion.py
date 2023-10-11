h1,w1=map(int,input().split())
a=[]
for i in range(h1):
    tmp=list(map(int,input().split()))
    a.append(tmp)
h2,w2=map(int,input().split())
b=[]
for i in range(h2):
    tmp=list(map(int,input().split()))
    b.append(tmp)
for bit_r in range(1<<h1):
    for bit_c in range(1<<w1):
        after=[]
        for shift_r in range(h1):
            if bit_r>>shift_r &1==0:
                after_row=[]
                for shift_c in range(w1):
                    if bit_c>>shift_c &1==0:
                        after_row.append(a[shift_r][shift_c])
            after.append(after_row)
        if after==b:
            print("Yes")
            exit()
print("No")