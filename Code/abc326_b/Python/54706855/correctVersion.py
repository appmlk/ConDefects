N = int(input())


def like326_Number(i):
    i_hundred = int(i/100)
    i_ten = int((i%100)/10)
    i_one = int(i%10)
    if i_hundred*i_ten == i_one:
        return True
    else:
        return False
        
for i in range(N,920):
    if like326_Number(i):
        print(i)
        exit()