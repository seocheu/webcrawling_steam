from bs4 import BeautifulSoup
import urllib.request
import pandas as pd
import re

def get_month(month) : # month -> 숫자
    if(month == 'Jan') : return 1
    elif(month == 'Feb') : return 2
    elif(month == 'Mar') : return 3
    elif(month == 'Apr') : return 4
    elif(month == 'May') : return 5
    elif(month == 'Jun') : return 6
    elif(month == 'Jul') : return 7
    elif(month == 'Aug') : return 8
    elif(month == 'Sep') : return 9
    elif(month == 'Oct') : return 10
    elif(month == 'Nov') : return 11
    elif(month == 'Dec') : return 12   
    
def parse_num(str) :    # 문자 -> 숫자
    return int(''.join(re.findall('\d', str)))

Steam_Games = list()
for page in range(1, 13) :
    Steam_Store_URL = f'https://store.steampowered.com/search/?sort_by=&sort_order=0&filter=topsellers&supportedlang=english&os=win&page={page}'

    html = urllib.request.urlopen(Steam_Store_URL)
    soup_steam = BeautifulSoup(html, 'html.parser')
    game_list = soup_steam.find('div', id='search_resultsRows')
    for game_info in game_list.find_all('a') :
        game = game_info.find('div', class_='responsive_search_name_combined')
        tag_span = game.find_all('span')
        tag_div = game.find_all('div')
        
        # 게임 스토어 아이디
        game_id = int(game_info.attrs['data-ds-appid'])
        
        # 게임 이름 
        game_name = tag_span[0].string  
        
        # 게임 출시일
        game_release_date = re.findall('\w+', tag_div[2].string) # day, month, year
        if len(game_release_date) == 2 :    # month, year만 있는 결우
            game_release_year = int(game_release_date[1])
            game_release_month = get_month(game_release_date[0])
        else :
            game_release_year = int(game_release_date[2])
            game_release_month = get_month(game_release_date[1])
        
        # 게임 평가
        tag_span = tag_div[3].find('span')
        if(tag_span is not None) : 
            review_attrs = tag_span.attrs
            game_review = ''.join(re.findall('\d+%', str(review_attrs)))
            game_rating = parse_num(str(game_review))
        else :
            game_rating = -1
            
        # 게임 가격 - 할인이 있는 경우 div는 11개 (없는 경우 9개)
        if(len(tag_div) == 9) :
            if(tag_div[8].string == 'Free') :
                game_original_price = 0  
            else :
                game_original_price = parse_num(tag_div[8].string)
            game_discount_percent = 0
            game_final_price = game_original_price
        elif(len(tag_div) < 9) :    # 예외 - 패키지 상품
            continue
        else :
            game_original_price = parse_num(game.
                find('div', class_='discount_original_price').string)
            game_discount_percent = parse_num(tag_div[7].string)
            game_final_price = parse_num(game.
                find('div', class_='discount_final_price').string)
        
        Steam_Games.append([game_id] + [game_name] + [game_rating] + [game_release_year] + 
                        [game_release_month] + [game_original_price] + 
                        [game_discount_percent] + [game_final_price])
        
    print(Steam_Games)

# id, 이름, 평점, 년도, 월, 일, 출시 가격, 할인율, 최종 가격
Games_tbl = pd.DataFrame(data=Steam_Games,
                        columns=('게임 번호', '이름', '유저 평가', '출시년도', '출시월',
                                '출시 가격', '할인율', '최종 가격'))

Games_tbl.to_csv('C:\\Users\seoch\Desktop\Code\pj2_file\Game_List_EXCEL.csv',
                encoding='cp949', mode='w', index=True)
Games_tbl.to_csv('C:\\Users\seoch\Desktop\Code\pj2_file\Game_List.csv',
                encoding='utf-8', mode='w', index=False)
