import json
from map_csp import *

class AustraliaMap(MapCSP):
    # Map of territories of Australia
    def __init__(self):
        states = ['WA', 'NT', 'SA', 'Q', 'NSW', 'V', 'T']
        neighbours = {'WA': ['NT', 'SA'],
                        'NT': ['WA', 'Q', 'SA'],
                        'SA': ['NT', 'Q', 'NSW', 'V', 'WA'],
                        'Q': ['NSW', 'SA', 'NT'],
                        'NSW': ['Q', 'V', 'SA'],
                        'V': ['NSW', 'SA'],
                        'T': []
                        }
        super().__init__(states, neighbours)
        self.color_options = ['red', 'green', 'blue']

class USSRMap(MapCSP):
    # Map of former USSR countries
    def __init__(self):
        states = ['Russia', 'Estonia', 'Lativa', 'Lithuania', 'Belarus', 'Ukraine', 'Moldova',
            'Georgia', 'Armenia', 'Azerbaijan', 'Turkmenistan', 'Uzbekistan', 'Kazakhstan',
            'Kyrgyzstan', 'Tajikistan', 'UyghurSSR', 'Mongolia', 'ManchuSSR']
        neighbours = {
            'Russia': ['Estonia', 'Lativa', 'Lithuania', 'Belarus', 'Ukraine', 'Georgia',
                'Azerbaijan', 'Kazakhstan', 'UyghurSSR', 'Mongolia', 'ManchuSSR'],
            'Estonia': ['Russia', 'Lativa'],
            'Lativa': ['Russia', 'Estonia', 'Lithuania', 'Belarus'],
            'Lithuania': ['Russia', 'Lativa', 'Belarus'],
            'Belarus': ['Russia', 'Lativa', 'Lithuania', 'Ukraine'],
            'Ukraine': ['Russia', 'Belarus', 'Moldova'],
            'Moldova': ['Ukraine'],
            'Georgia': ['Russia', 'Armenia', 'Azerbaijan'],
            'Armenia': ['Georgia', 'Azerbaijan'],
            'Azerbaijan': ['Russia', 'Georgia', 'Armenia'],
            'Turkmenistan': ['Uzbekistan', 'Kazakhstan'],
            'Uzbekistan': ['Turkmenistan', 'Kazakhstan', 'Kyrgyzstan', 'Tajikistan'],
            'Kazakhstan': ['Russia', 'Turkmenistan', 'Uzbekistan', 'Kyrgyzstan', 'UyghurSSR'],
            'Kyrgyzstan': ['Uzbekistan', 'Kazakhstan', 'Tajikistan', 'UyghurSSR'],
            'Tajikistan': ['Kyrgyzstan', 'Uzbekistan', 'UyghurSSR'],
            'UyghurSSR': ['Russia', 'Kazakhstan', 'Kyrgyzstan', 'Tajikistan', 'Mongolia'],
            'Mongolia': ['Russia', 'UyghurSSR', 'ManchuSSR'],
            'ManchuSSR': ['Mongolia', 'Russia']
            }
        super().__init__(states, neighbours)

class USAMap(MapCSP):
    # Map of USA states
    def __init__(self):
        states = ['AK', 'AL', 'AR', 'AZ', 'CA', 'CO', 'CT', 'DC', 'DE', 'FL', 'GA', 'HI', 'IA',
            'ID', 'IL', 'IN', 'KA', 'KY', 'LA', 'MA', 'MD', 'ME', 'MI', 'MN', 'MO', 'MS', 'MT',
            'NC', 'ND', 'NE', 'NH', 'NJ', 'NM', 'NV', 'NY', 'OH', 'OK', 'OR', 'PA', 'RI', 'SC',
            'SD', 'TN', 'TX', 'UT', 'VA', 'VT', 'WA', 'WI', 'WV', 'WY',]
        neighbours = {
            'AK': [], 'AL': ['FL', 'GA', 'MS', 'TN'], 'AR': ['LA', 'MO', 'MS', 'OK', 'TN', 'TX'],
            'AZ': ['CA', 'NV', 'UT'], 'CA': ['AZ', 'NV', 'NY', 'OR'],
            'CO': ['KA', 'NE', 'NM', 'OK', 'UT', 'WY'], 'CT': ['MA', 'RI'], 'DC': ['MD', 'VA'],
            'DE': ['MD', 'NJ', 'PA'], 'FL': ['AL', 'GA'], 'GA': ['AL', 'FL', 'NC', 'SC', 'TN'],
            'HI': [], 'IA': ['IL', 'MN', 'MO', 'NE', 'SD', 'WI'],
            'ID': ['MT', 'NV', 'OR', 'UT', 'WA', 'WY'], 'IL': ['IA', 'IN', 'MO', 'WI'],
            'IN': ['IL', 'KY'], 'KA': ['CO', 'MO', 'NE', 'OK'],
            'KY': ['IN', 'MO', 'OH', 'TN', 'VA', 'WV'], 'LA': ['AR', 'MS', 'TX'],
            'MA': ['CT', 'NH', 'NY', 'RI', 'VT'], 'MD': ['DC', 'DE', 'PA', 'VA', 'WV'],
            'ME': ['NH'], 'MI': ['OH', 'WI'], 'MN': ['IA', 'ND', 'SD', 'WI'],
            'MO': ['AR', 'IA', 'IL', 'KA', 'KY', 'NE', 'OK', 'TN'], 'MS': ['AL', 'AR', 'LA', 'TN'],
            'MT': ['ID', 'ND', 'SD', 'WY'], 'NC': ['GA', 'SC', 'TN', 'VA'],
            'ND': ['MN', 'MT', 'SD'], 'NE': ['CO', 'IA', 'KA', 'MO', 'SD', 'WY'],
            'NH': ['MA', 'ME', 'VT'], 'NJ': ['DE', 'NY', 'PA'], 'NM': ['CO', 'OK', 'TX'],
            'NV': ['AZ', 'CA', 'ID', 'OR', 'UT'], 'NY': ['CA', 'MA', 'NJ', 'PA', 'VT'],
            'OH': ['KY', 'MI', 'PA', 'WV'], 'OK': ['AR', 'CO', 'KA', 'MO', 'NM', 'TX'],
            'OR': ['CA', 'ID', 'NV', 'WA'], 'PA': ['DE', 'MD', 'NJ', 'NY', 'OH', 'WV'],
            'RI': ['CT', 'MA'], 'SC': ['GA', 'NC'], 'SD': ['IA', 'MN', 'MT', 'ND', 'NE', 'WY'],
            'TN': ['AL', 'AR', 'GA', 'KY', 'MO', 'MS', 'NC', 'VA'], 'TX': ['AR', 'LA', 'NM', 'OK'],
            'UT': ['AZ', 'CO', 'ID', 'NV', 'WY'], 'VA': ['DC', 'KY', 'MD', 'NC', 'TN', 'WV'],
            'VT': ['MA', 'NH', 'NY'], 'WA': ['ID', 'OR'], 'WI': ['IA', 'IL', 'MI', 'MN'],
            'WV': ['KY', 'MD', 'OH', 'PA', 'VA'], 'WY': ['CO', 'ID', 'MT', 'NE', 'SD', 'UT']
            }
        super().__init__(states, neighbours)

class WorldMap(MapCSP):
    # Map of countries in the wolrd
    def __init__(self):
        data = json.load(open('world.json', 'r'))
        states = [s['cca3'] for s in data]
        neighbours = {s['cca3']: s['borders'] for s in data}
        # Some borders are not marked symetrically
        for s1, neigs in neighbours.items():
            for s2 in neigs:
                if s1 not in neighbours[s2]:
                    neighbours[s2].append(s1)
        super().__init__(states, neighbours)

class ImpossibleMap(MapCSP):
    # Makes map impossible to color
    def __init__(self, other_map):
        super().__init__(other_map.states, other_map.neighbours)
        self.color_options = other_map.color_options[:-1]
