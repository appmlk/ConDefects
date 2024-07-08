n, m = map(int, input().split())
s = str(input())
m_real = m
logo_T = 0
logo_T_real = 0
for i in range (n):
    if s[i] == "0":
        m_real = m
        logo_T_real = logo_T
    if s[i] == "1":
        if m_real > 0:
            m_real -= 1
        else:
            logo_T += 1
    if s[i] == "2":
        if logo_T_real > 0:
            logo_T_real -= 1
        else:
            logo_T += 1
print(logo_T)